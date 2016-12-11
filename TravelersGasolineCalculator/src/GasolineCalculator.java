import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GasolineCalculator extends JApplet implements ActionListener, ItemListener {
  private static final long   serialVersionUID = 7185982631853967288L;
  private JFrame              jf;
  double                      tripDistance, gasCost, milesPerGallon;
  String                      total;
  private final static String LOOKANDFEEL      = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
  
  JPanel headerPanel;
  JPanel footerPanel;
  JPanel userInputPanel;
  JPanel tripDistancePanel, vehicleTypePanel, gasolineTypePanel, beginningLocationsPanel, destinationLocationsPanel;
  
  Label applicationNameLabel = new Label("Traveler's Gasoline Calculator", Label.CENTER);
  
  Label addressLabel              = new Label("410 Terry Avenue North, Seattle, WA, 98109", Label.CENTER);
  Label descriptionLabel          = new Label(
      "<html>The Traveler's Gasoline Calculator allows users to enter values for the number of miles, the number of gallons, and the cost of gasoline. <br>It also enables users to reset the value on the screen to zero (0) so that another calculation can be performed.</html>",
      Label.CENTER);
  Label promptLabel               = new Label(
      "Please enter your Trip Distance, Vehicle Type and Gasoline Type below: \n", Label.CENTER);
  Label tripDistanceLabel         = new Label("Approximate Miles ", Label.RIGHT);
  Label vehicleTypeLabel          = new Label("Vehicle Type ", Label.RIGHT);
  Label gasolineTypeLabel         = new Label("gasolineType ", Label.RIGHT);
  Label travelCostLabel           = new Label("Your estimated Travel Cost is:", Label.CENTER);
  Label flashLabel                = new Label("", Label.CENTER);
  Label outputLabel               = new Label("Click the Submit button to see your total travel cost", Label.CENTER);
  Label calculationLabel          = new Label();
  Label gasCostLabel              = new Label("Cost of Gas/Gallon ", Label.RIGHT);
  Label beginningLocationsLabel   = new Label("Starting Location ", Label.RIGHT);
  Label destinationLocationsLabel = new Label("Destination ", Label.RIGHT);
  
  JTextField gasCostField      = new JTextField(10);
  JTextField tripDistanceField = new JTextField(10);
  
  Button saveButton   = new Button("Save Travel Record");
  Button submitButton = new Button("Submit");
  Button clearButton  = new Button("Clear");
  
  Choice   gasDropdown                  = new Choice();
  Choice   vehicleDropdown              = new Choice();
  Choice   beginningLocationsDropdown   = new Choice();
  Choice   destinationLocationsDropdown = new Choice();
  String[] gasOptions                   = new String[] { "Leaded", "Unleaded", "Super Unleaded", "Diesel" };
  String[] vehicleOptions               = new String[] { "Compact", "Mid-Size", "Luxury", "SUV" };
  String[] beginningLocationsOptions    = new String[] { "Seattle", "Portland", "San Franciso", "Denver", "Minneapolis",
      "Chicago", "Miami", "Philadelphia", "New York City", "Boston" };
  String[] destinationLocationsOptions  = new String[] { "Seattle", "Portland", "San Franciso", "Denver", "Minneapolis",
      "Chicago", "Miami", "Philadelphia", "New York City", "Boston" };
  
  public void init() {
    jf = new JFrame();
    jf.setLayout(new GridLayout(3, 1));
    milesPerGallon = 15.00;
    setUILook();
    populateMenuOptions(gasDropdown, gasOptions);
    populateMenuOptions(vehicleDropdown, vehicleOptions);
    populateMenuOptions(beginningLocationsDropdown, beginningLocationsOptions);
    populateMenuOptions(destinationLocationsDropdown, destinationLocationsOptions);
    
    configureHeaderPanel();
    configureUserInputPanels();
    configureFooterPanel();
    
    jf.add(headerPanel);
    jf.add(userInputPanel);
    jf.add(footerPanel);
    
    jf.pack();
    jf.setVisible(true);
    
    saveButton.addActionListener(this);
    submitButton.addActionListener(this);
    clearButton.addActionListener(this);
    vehicleDropdown.addItemListener(this);
    gasDropdown.addItemListener(this);
    
    resize(300, 150);
    
  }
  
  private void setUILook() {
    try {
      UIManager.setLookAndFeel(LOOKANDFEEL);
    } catch (ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      System.err.println("Couldn't find class for specified look and feel: " + LOOKANDFEEL);
      System.err.println("Using the default look and feel.");
    } catch (InstantiationException ie) {
      ie.printStackTrace();
      System.err.println("Couldn't create a new instance for specified look and feel: " + LOOKANDFEEL);
      System.err.println("Using the default look and feel.");
    } catch (IllegalAccessException iae) {
      iae.printStackTrace();
      System.err.println(
          "Couldn't access the class, field, method or constructor associated with look and feel: " + LOOKANDFEEL);
      System.err.println("Using the default look and feel.");
    } catch (UnsupportedLookAndFeelException ulfe) {
      ulfe.printStackTrace();
      System.err.println("The requested look and feel: " + LOOKANDFEEL + " is not present on your system");
      System.err.println("Using the default look and feel.");
    }
  }
  
  private void configureHeaderPanel() {
    headerPanel = new JPanel(new GridLayout(3, 1));
    Panel centerHeaderPanel = new Panel(new BorderLayout());
    
    Font appHeaderFont = new Font("Verdana", Font.BOLD, 24);
    ImageIcon companyLogo = new ImageIcon("../resources/companyLogo.jpg");
    JLabel picLabel = new JLabel(companyLogo);
    
    applicationNameLabel.setFont(appHeaderFont);
    headerPanel.add(applicationNameLabel);
    centerHeaderPanel.add(picLabel, BorderLayout.NORTH);
    centerHeaderPanel.add(addressLabel, BorderLayout.SOUTH);
    headerPanel.add(centerHeaderPanel);
    headerPanel.add(descriptionLabel);
  }
  
  private void configureFooterPanel() {
    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
    JPanel outputPanel = new JPanel(new GridLayout(4, 1));
    footerPanel = new JPanel(new GridLayout(3, 1));
    
    outputPanel.add(calculationLabel);
    outputPanel.add(outputLabel);
    outputPanel.add(travelCostLabel);
    outputPanel.add(flashLabel);
    
    calculationLabel.setAlignment(WIDTH);
    
    submitButton.setForeground(Color.green);
    saveButton.setForeground(Color.blue);
    clearButton.setForeground(Color.red);
    buttonPanel.add(submitButton);
    buttonPanel.add(saveButton);
    buttonPanel.add(clearButton);
    
    footerPanel.add(outputPanel);
    footerPanel.add(calculationLabel);
    footerPanel.add(buttonPanel);
  }
  
  private void configureUserInputPanels() {
    JPanel gasPanel = new JPanel(new GridLayout(3, 2));
    JPanel tripPanel = new JPanel(new GridLayout(3, 2));
    JPanel vehiclePanel = new JPanel(new GridLayout(1, 2));
    JPanel inputPanel = new JPanel();
    userInputPanel = new JPanel(new GridLayout(2, 1));
    
    gasPanel.add(gasCostLabel);
    gasPanel.add(gasCostField);
    gasPanel.add(gasolineTypeLabel);
    gasPanel.add(gasDropdown);
    
    tripPanel.add(tripDistanceLabel);
    tripPanel.add(tripDistanceField);
    tripPanel.add(beginningLocationsLabel);
    tripPanel.add(beginningLocationsDropdown);
    tripPanel.add(destinationLocationsLabel);
    tripPanel.add(destinationLocationsDropdown);
    
    vehiclePanel.add(vehicleTypeLabel);
    vehiclePanel.add(vehicleDropdown);
    
    inputPanel.add(tripPanel);
    inputPanel.add(vehiclePanel);
    inputPanel.add(gasPanel);
    
    userInputPanel.add(promptLabel);
    userInputPanel.add(inputPanel);
  }
  
  private void populateMenuOptions(Choice menu, String[] options) {
    for (String opt : options) {
      menu.add(opt);
    }
  };
  
  public void actionPerformed(ActionEvent e) {
    String vehicleType = vehicleDropdown.getSelectedItem();
    
    try {
      tripDistance = Double.parseDouble(tripDistanceField.getText());
    } catch (NumberFormatException nfe) {
    }
    
    try {
      gasCost = Double.parseDouble(gasCostField.getText());
    } catch (NumberFormatException nfe) {
    }
    
    if (e.getSource() == submitButton) {
      total = calculateTravelersCost(tripDistance, milesPerGallon, gasCost);
      outputTravelersCost(tripDistance, vehicleType, total);
    } else if (e.getSource() == clearButton) {
      resetUserInputs();
    } else if (e.getSource() == saveButton) {
      total = calculateTravelersCost(tripDistance, milesPerGallon, gasCost);
      outputTravelersCost(tripDistance, vehicleType, total);
      try {
        writeDataToFile();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
    }
  }
  
  private void writeDataToFile() throws IOException {
    FileWriter fw = null;
    File textDB = new File("travelrecords.txt");
    
    try {
      fw = new FileWriter(textDB, true);
      Date tripDate = new Date();
      String startingLocation = beginningLocationsDropdown.getSelectedItem();
      String destination = destinationLocationsDropdown.getSelectedItem();
      String approxMiles = tripDistanceField.getText();
      String cost = calculationLabel.getText();
      
      fw.write("=====================================\n");
      fw.write("date: " + tripDate + "\n");
      fw.write("startingLocation: " + startingLocation + "\n");
      fw.write("destination: " + destination + "\n");
      fw.write("approxMiles: " + approxMiles + "\n");
      fw.write("cost: " + cost + "\n");
      
      flashLabel.setText("Successfully saved your travel record to " + textDB.getPath());
    } catch (IOException e) {
      flashLabel.setText("Could not save travel record to a file");
      e.printStackTrace();
    }
    fw.close();
  }
  
  private void resetUserInputs() {
    tripDistanceField.setText("");
    gasCostField.setText("");
    vehicleDropdown.select(0);
    gasDropdown.select(0);
    calculationLabel.setText("");
    flashLabel.setText("");
  }
  
  private void outputTravelersCost(double tripDistance, String vehicleType, String total) {
    calculationLabel.setText(total);
  };
  
  private String calculateTravelersCost(double tripDistance, double milesPerGallon, double gasCost) {
    Double totalOilChangeCost = calculateOilChangeCost(tripDistance);
    NumberFormat formatter = NumberFormat.getCurrencyInstance();
    
    return formatter.format(((tripDistance / milesPerGallon) * gasCost) + totalOilChangeCost);
  };
  
  private Double calculateOilChangeCost(double distance) {
    int numberOfOilChanges = 0;
    final int OILCHANGEFREQUENCY = 3000;
    final double singleOilChangeCost = 30.00;
    double totalOilChangeCost = 0.00;
    
    if (tripDistance >= OILCHANGEFREQUENCY) {
      numberOfOilChanges = (int) Math.floor(distance / OILCHANGEFREQUENCY);
      totalOilChangeCost = numberOfOilChanges * singleOilChangeCost;
    }
    
    return totalOilChangeCost;
  }
  
  private void setGasolineCost(String gasolineType) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("Leaded", "2.50");
    map.put("Unleaded", "2.90");
    map.put("Super Unleaded", "3.00");
    map.put("Diesel", "4.00");
    
    gasCostField.setText(map.get(gasolineType));
  }
  
  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getSource() == gasDropdown) {
      setGasolineCost(gasDropdown.getSelectedItem());
    }
  }
  
}
