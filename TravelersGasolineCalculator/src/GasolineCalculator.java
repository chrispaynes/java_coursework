import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GasolineCalculator extends Applet implements ActionListener, ItemListener {
  Image  companyLogo;
  String gasolineType, vehicleType;
  double tripDistance, gasCost, total, milesPerGallon;
  
  Panel headerPanel, centerHeaderPanel;
  Panel footerPanel, buttonPanel, outputPanel;
  Panel userInputNorthPanel, userInputPanel, inputPanel, gasPricePanel;
  Panel tripDistancePanel, vehicleTypePanel, gasolineTypePanel;
  
  Label applicationNameLabel = new Label("Traveler's Gasoline Calculator", Label.CENTER);
  Label addressLabel         = new Label("410 Terry Avenue North, Seattle, WA, 98109", Label.CENTER);
  Label descriptionLabel     = new Label(
      "<html>The Traveler's Gasoline Calculator allows users to enter values for the number of miles, the number of gallons, and the cost of gasoline. <br>It also enables users to reset the value on the screen to zero (0) so that another calculation can be performed.</html>",
      Label.CENTER);
  Label promptLabel          = new Label("Please enter your Trip Distance, Vehicle Type and Gasoline Type below: \n",
      Label.CENTER);
  Label tripDistanceLabel    = new Label("Trip Distance", Label.RIGHT);
  Label vehicleTypeLabel     = new Label("Vehicle Type", Label.RIGHT);
  Label gasolineTypeLabel    = new Label("gasolineType", Label.RIGHT);
  Label travelCostLabel      = new Label("Your estimated Travel Cost is:");
  Label outputLabel          = new Label("Click the Calculate button to see your total travel cost");
  Label calculationLabel     = new Label();
  Label gasCostLabel         = new Label("Cost of Gas", Label.RIGHT);
  
  TextField gasCostField      = new TextField(10);
  TextField tripDistanceField = new TextField(10);
  
  Button calculateButton = new Button("Calculate");
  Button resetButton     = new Button("Reset");
  
  Choice   gasDropdown     = new Choice();
  Choice   vehicleDropdown = new Choice();
  String[] gasOptions      = new String[] { "Leaded", "Unleaded", "Super Unleaded", "Diesel" };
  String[] vehicleOptions  = new String[] { "Compact", "Mid-Size", "Luxury", "SUV" };
  
  public void init() {
    milesPerGallon = 15.00;
    populateMenuOptions(gasDropdown, gasOptions);
    populateMenuOptions(vehicleDropdown, vehicleOptions);
    this.setLayout(new BorderLayout());
    setBackground(new Color(211, 211, 211));
    
    configureHeaderPanel();
    configureUserInputPanels();
    configureFooterPanel();
    
    add(headerPanel, BorderLayout.NORTH);
    add(userInputPanel, BorderLayout.CENTER);
    add(footerPanel, BorderLayout.SOUTH);
    
    calculateButton.addActionListener(this);
    resetButton.addActionListener(this);
    vehicleDropdown.addItemListener(this);
    gasDropdown.addItemListener(this);
    
  }
  
  public void configureFooterPanel() {
    footerPanel = new Panel(new BorderLayout());
    buttonPanel = new Panel(new BorderLayout());
    outputPanel = new Panel(new BorderLayout());
    
    outputPanel.add(travelCostLabel);
    outputPanel.add(calculationLabel);
    footerPanel.add(outputPanel, BorderLayout.NORTH);
    calculationLabel.setAlignment(WIDTH);
    footerPanel.add(calculationLabel, BorderLayout.CENTER);
    calculateButton.setForeground(Color.green);
    buttonPanel.add(calculateButton, BorderLayout.WEST);
    resetButton.setForeground(Color.red);
    buttonPanel.add(resetButton, BorderLayout.EAST);
    footerPanel.add(buttonPanel, BorderLayout.SOUTH);
  }
  
  public void configureHeaderPanel() {
    headerPanel = new Panel(new BorderLayout());
    centerHeaderPanel = new Panel(new BorderLayout());
    
    Font appHeaderFont = new Font("Verdana", Font.BOLD, 24);
    companyLogo = getImage(getDocumentBase(), "../resources/companyLogo.jpg");
    JLabel picLabel = new JLabel(new ImageIcon(companyLogo));
    
    applicationNameLabel.setFont(appHeaderFont);
    headerPanel.add(applicationNameLabel, BorderLayout.NORTH);
    centerHeaderPanel.add(picLabel, BorderLayout.NORTH);
    centerHeaderPanel.add(addressLabel, BorderLayout.CENTER);
    headerPanel.add(centerHeaderPanel, BorderLayout.CENTER);
    headerPanel.add(descriptionLabel, BorderLayout.SOUTH);
  }
  
  public void configureUserInputPanels() {
    userInputPanel = new Panel(new BorderLayout());
    inputPanel = new Panel();
    userInputNorthPanel = new Panel(new BorderLayout());
    
    tripDistancePanel = new Panel();
    tripDistancePanel.add(tripDistanceLabel);
    tripDistancePanel.add(tripDistanceField);
    
    gasPricePanel = new Panel();
    gasPricePanel.add(gasCostLabel);
    gasPricePanel.add(gasCostField);
    
    userInputNorthPanel.add(tripDistancePanel, BorderLayout.NORTH);
    userInputNorthPanel.add(gasPricePanel, BorderLayout.CENTER);
    
    vehicleTypePanel = new Panel();
    vehicleTypePanel.add(vehicleTypeLabel);
    vehicleTypePanel.add(vehicleDropdown);
    
    gasolineTypePanel = new Panel();
    gasolineTypePanel.add(gasolineTypeLabel);
    gasolineTypePanel.add(gasDropdown);
    
    inputPanel.add(userInputNorthPanel, BorderLayout.NORTH);
    inputPanel.add(vehicleTypePanel, BorderLayout.CENTER);
    inputPanel.add(gasolineTypePanel, BorderLayout.SOUTH);
    
    userInputPanel.add(promptLabel, BorderLayout.NORTH);
    userInputPanel.add(inputPanel, BorderLayout.CENTER);
  }
  
  public void populateMenuOptions(Choice menu, String[] options) {
    for (String opt : options) {
      menu.add(opt);
    }
  };
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == calculateButton) {
      tripDistance = Double.parseDouble(tripDistanceField.getText());
      vehicleType = vehicleDropdown.getSelectedItem();
      gasolineType = gasDropdown.getSelectedItem();
      gasCost = Double.parseDouble(gasCostField.getText());
      resetUserInputs();
      
    } else if (e.getSource() == resetButton) {
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
  
  public void paint(Graphics g) {
    
  }
  
  @Override
  public void itemStateChanged(ItemEvent e) {
    // TODO Auto-generated method stub
    
  }
  
}
