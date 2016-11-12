package RestaurantReservation;

/*
 * This is an external class called by the Reservations.java program. Its
 * constructor method receives the number of non-smoking and smoking rooms and
 * then creates an array of empty rooms. The bookRoom() method accepts a boolean
 * value and returns a room; number.
 */

public class Table {
  private final int numberOfSmokingTables;
  private final int numberOfNonSmokingTables;
  boolean           numberOfOccupiedTables[];
  
  public Table(int non, int sm) {
    numberOfOccupiedTables = new boolean[sm + non];
    
    setOccupiedTables(sm + non);
    numberOfSmokingTables = sm;
    numberOfNonSmokingTables = non;
  };
  
  private void setOccupiedTables(int tableCount) {
    for (int i = 0; i < tableCount; i++) {
      numberOfOccupiedTables[i] = false;
    }
  };
  
  public int bookTable(boolean smoking) {
    int begin, end;
    
    if (!smoking) {
      begin = 0;
      end = numberOfNonSmokingTables;
    } else {
      begin = numberOfNonSmokingTables;
      end = numberOfSmokingTables + numberOfNonSmokingTables;
    }
    
    return determineAvailableRooms(begin, end);
  }
  
  private int determineAvailableRooms(int begin, int end) {
    int roomNumber = 0;
    for (int i = begin; i < end; ++i) {
      if (!numberOfOccupiedTables[i]) {
        numberOfOccupiedTables[i] = true;
        roomNumber = i + 1;
        i = end;
      }
    }
    return roomNumber;
  };
  
  public int getNumberOfSmokingTables() {
    return this.numberOfSmokingTables;
  };
  
  public int getNumberOfNonSmokingTables() {
    return this.numberOfNonSmokingTables;
  };
}
