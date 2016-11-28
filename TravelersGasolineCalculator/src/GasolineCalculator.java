import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GasolineCalculator extends JApplet implements ActionListener, ItemListener {
  private static final long serialVersionUID = 7185982631853967288L;
  JFrame                    jf;
  Image                     companyLogo;
  String                    gasolineType, vehicleType;
  double                    tripDistance, gasCost, total, milesPerGallon;
  
  Panel  centerHeaderPanel;
  JPanel headerPanel;
  JPanel footerPanel, buttonPanel, outputPanel;
  JPanel userInputPanel;
  JPanel tripDistancePanel, vehicleTypePanel, gasolineTypePanel, beginningLocationsPanel, destinationLocationsPanel;
  
  Label applicationNameLabel = new Label("Traveler's Gasoline Calculator", Label.CENTER);
  
  Label addressLabel              = new Label("410 Terry Avenue North, Seattle, WA, 98109", Label.CENTER);
  Label descriptionLabel          = new Label(
      "<html>The Traveler's Gasoline Calculator allows users to enter values for the number of miles, the number of gallons, and the cost of gasoline. <br>It also enables users to reset the value on the screen to zero (0) so that another calculation can be performed.</html>",
      Label.CENTER);
  Label promptLabel               = new Label(
      "Please enter your Trip Distance, Vehicle Type and Gasoline Type below: \n", Label.CENTER);
  Label tripDistanceLabel         = new Label("Approximate Miles", Label.RIGHT);
  Label vehicleTypeLabel          = new Label("Vehicle Type", Label.RIGHT);
  Label gasolineTypeLabel         = new Label("gasolineType", Label.RIGHT);
  Label travelCostLabel           = new Label("Your estimated Travel Cost is:", Label.CENTER);
  Label outputLabel               = new Label("Click the Submit button to see your total travel cost", Label.CENTER);
  Label calculationLabel          = new Label();
  Label gasCostLabel              = new Label("Cost of Gas/Gallon", Label.RIGHT);
  Label beginningLocationsLabel   = new Label("Starting Location", Label.RIGHT);
  Label destinationLocationsLabel = new Label("Destination", Label.RIGHT);
  
  JTextField gasCostField      = new JTextField(10);
  TextField  tripDistanceField = new TextField(10);
  
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
    
    submitButton.addActionListener(this);
    clearButton.addActionListener(this);
    vehicleDropdown.addItemListener(this);
    gasDropdown.addItemListener(this);
    
  }
  
  public void configureHeaderPanel() {
    headerPanel = new JPanel(new GridLayout(3, 1));
    centerHeaderPanel = new Panel(new BorderLayout());
    
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
  
  public void configureFooterPanel() {
    footerPanel = new JPanel(new GridLayout(3, 1));
    buttonPanel = new JPanel(new GridLayout(1, 2));
    outputPanel = new JPanel(new GridLayout(3, 1));
    
    outputPanel.add(calculationLabel);
    outputPanel.add(outputLabel);
    outputPanel.add(travelCostLabel);
    
    calculationLabel.setAlignment(WIDTH);
    
    submitButton.setForeground(Color.green);
    clearButton.setForeground(Color.red);
    buttonPanel.add(submitButton);
    buttonPanel.add(clearButton);
    
    footerPanel.add(outputPanel);
    footerPanel.add(calculationLabel);
    footerPanel.add(buttonPanel);
    
  }
  
  public void configureUserInputPanels() {
    JPanel gasPanel = new JPanel(new GridLayout(2, 2));
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
  
  public void populateMenuOptions(Choice menu, String[] options) {
    for (String opt : options) {
      menu.add(opt);
    }
  };
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
      tripDistance = Double.parseDouble(tripDistanceField.getText());
      vehicleType = vehicleDropdown.getSelectedItem();
      gasolineType = gasDropdown.getSelectedItem();
      gasCost = Double.parseDouble(gasCostField.getText());
      resetUserInputs();
      
    } else if (e.getSource() == clearButton) {
      resetUserInputs();
    }
    total = calculateTravelersCost(tripDistance, milesPerGallon, gasCost);
    outputTravelersCost(tripDistance, vehicleType, total);
  }
  
  public void resetUserInputs() {
    tripDistanceField.setText(" ");
    gasCostField.setText(" ");
    vehicleDropdown.select(0);
    gasDropdown.select(0);
    calculationLabel.setText(" ");
  }
  
  public void outputTravelersCost(double tripDistance, String vehicleType, double total) {
    // calculationLabel.setText("<html>Your total travel cost for driving " +
    // tripDistance + " miles<br>" + " in a "
    // + vehicleType + " is $" + total + ".</html>");
    calculationLabel.setText("$" + total);
  };
  
  public Double calculateTravelersCost(double tripDistance, double milesPerGallon, double gasCost) {
    return (tripDistance / milesPerGallon) * gasCost;
  };
  
  public Double setGasolineCost(String gasolineType) {
    Double costPerGallon;
    
    switch (gasolineType) {
    case "leaded":
      costPerGallon = 2.50;
      break;
    case "unleaded":
      costPerGallon = 2.90;
      break;
    case "super unleaded":
      costPerGallon = 3.00;
      break;
    case "diesel":
      costPerGallon = 4.00;
      break;
    default:
      costPerGallon = 0.00;
    }
    
    return costPerGallon;
  }
  
  @Override
  public void itemStateChanged(ItemEvent e) {
    // TODO Auto-generated method stub
    
  }
  
}
