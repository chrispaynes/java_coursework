package stockTracker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MakeDB {
  
  public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
    Connection connection = null;
    
    try {
      Class.forName("org.postgresql.Driver");
      String url = "jdbc:postgresql://localhost:5432/stocktracker";
      connection = DriverManager.getConnection(url, "postgres", "ganymede101");
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getClass().getName() + ": " + e.getMessage());
      System.exit(0);
    }
    Statement stmt = connection.createStatement();
    
    System.out.println("Opened database successfully");
    System.out.println(stmt);
    
    System.out.println("\nDropping indexes & tables...\n");
    
    try {
      stmt.executeUpdate("DROP INDEX PK_UserStocks ON UserStocks");
    } catch (Exception e) {
      System.out.println("Could not drop primary key on UserStocks table: " + e.getMessage());
    }
    
    try {
      stmt.executeUpdate("DROP TABLE UserStocks");
    } catch (Exception e) {
      System.out.println("Could not drop UserStocks table: " + e.getMessage());
    }
    
    try {
      stmt.executeUpdate("DROP TABLE Users");
    } catch (Exception e) {
      System.out.println("Could not drop Users table: " + e.getMessage());
    }
    
    try {
      stmt.executeUpdate("DROP TABLE Stocks");
    } catch (Exception e) {
      System.out.println("Could not drop Stocks table: " + e.getMessage());
    }
    
    // CREATE DATABASE TABLES //
    System.out.println("\nCreating Tables .............");
    
    // Create Stocks table with primary key index
    try {
      System.out.println("Creating Stocks table with primary key index...");
      stmt.executeUpdate("CREATE TABLE Stocks (" + "symbol VARCHAR(8) NOT NULL " + "CONSTRAINT PK_Stocks PRIMARY KEY, "
          + "name VARCHAR(50))");
    } catch (Exception e) {
      System.out.println("Exception creating Stocks table: " + e.getMessage());
    }
    
    // Create Users table with primary key index
    try {
      System.out.println("Creating Users table with primary key index...");
      stmt.executeUpdate("CREATE TABLE Users (" + "userID VARCHAR(20) NOT NULL " + "CONSTRAINT PK_Users PRIMARY KEY, "
          + "lastName VARCHAR(30) NOT NULL, " + "firstName VARCHAR(30) NOT NULL, " + "pswd BYTEA, " + "admin BOOL"
          + ")");
    } catch (Exception e) {
      System.out.println("Exception creating Users Table: " + e.getMessage());
    }
    
    // Create userStocks table with foreign keys to Users and Stocks tables
    try {
      System.out.println("Creating UserStocks table ...");
      stmt.executeUpdate("CREATE TABLE UserStocks (" + "userID VARCHAR(20) "
          + "CONSTRAINT FK1_UserStocks REFERENCES Users (userID), " + "symbol VARCHAR(8), "
          + "CONSTRAINT FK2_UserStocks FOREIGN KEY (symbol) " + "REFERENCES Stocks (symbol))");
    } catch (Exception e) {
      System.out.println("Exception Creating UserStocks table: " + e.getMessage());
    }
    
    // Create UserStocks table primary key index
    try {
      System.out.println("Creating UserStocks table primary key index ...");
      stmt.executeUpdate("CREATE UNIQUE INDEX PK_UserStocks " + "ON UserStocks (userID, symbol)");
    } catch (Exception e) {
      System.out.println("Exception creating UserStocks index: " + e.getMessage());
    }
    
    // Create one admin user with password as initial data
    String userID = "admin01";
    String firstName = "Default";
    String lastName = "Admin";
    String initialPswd = "admin01";
    Password pswd = null;
    
    try {
      pswd = new Password(initialPswd);
    } catch (PasswordException e) {
      e.printStackTrace();
    }
    
    boolean admin = true;
    
    PreparedStatement pStmt = connection
        .prepareStatement("INSERT INTO Users (USERID, FIRSTNAME, LASTNAME, PSWD, ADMIN) VALUES (?,?,?,?,?)");
    
    try {
      pStmt.setString(1, userID);
      pStmt.setString(2, lastName);
      pStmt.setString(3, firstName);
      pStmt.setBytes(4, serializeObj(pswd));
      pStmt.setBoolean(5, admin);
      pStmt.executeUpdate();
    } catch (Exception e) {
      System.out.println("Exception inserting user: " + e.getMessage());
    }
    
    pStmt.close();
    
    // Read and display all User Data in the database
    ResultSet rs = stmt.executeQuery("SELECT * FROM users");
    
    System.out.println("\n----------------------------------");
    System.out.println("DATABASE CREATED\n");
    System.out.println("Displaying data from database...\n");
    System.out.println("Users table contains:");
    
    Password pswdFromDB;
    byte[] buf = null;
    
    while (rs.next()) {
      System.out.println("LOGON ID = " + rs.getString("userID"));
      System.out.println("FIRST NAME = " + rs.getString("firstName"));
      System.out.println("LAST NAME = " + rs.getString("lastName"));
      System.out.println("ADMINISTRATIVE = " + rs.getString("admin"));
      System.out.println("INITIAL PASSWORD = " + initialPswd);
      
      buf = rs.getBytes("pswd");
      if (buf != null) {
        System.out.println("PASSWORD OBJECT = " + (pswdFromDB = (Password) deserializeObj(buf)));
        System.out.println("AUTO EXPIRES = " + pswdFromDB.getAutoExpires());
        System.out.println("EXPIRING NOW = " + pswdFromDB.isExpiring());
        System.out.println("REMAINING USES = " + pswdFromDB.getRemainingUses() + "\n");
      } else {
        System.out.println("Password Object = Null!");
      }
    }
    
    rs = stmt.executeQuery("SELECT * FROM stocks");
    if (!rs.next()) {
      System.out.println("Stocks table contains no records");
    } else {
      System.out.println("Stocks table still contains records!");
    }
    
    rs = stmt.executeQuery("SELECT * FROM userstocks");
    if (!rs.next()) {
      System.out.println("UserStocks table contains no records");
    } else {
      System.out.println("UserStocks table still contains records!");
    }
  }
  
  // Writes object to byte array then inserts into prepared statement
  private static byte[] serializeObj(Object obj) throws IOException {
    ByteArrayOutputStream baOStream = new ByteArrayOutputStream();
    ObjectOutputStream objOStream = new ObjectOutputStream(baOStream);
    
    objOStream.writeObject(obj);
    objOStream.flush();
    objOStream.close();
    return baOStream.toByteArray();
  }
  
  // Reads bytes from result set into byte array
  // Creates input stream and read the data into an object
  private static Object deserializeObj(byte[] buf) throws IOException, ClassNotFoundException {
    Object obj = null;
    if (buf != null) {
      ObjectInputStream objIStream = new ObjectInputStream(new ByteArrayInputStream(buf));
      obj = objIStream.readObject();
    }
    return obj;
  }
  
}
