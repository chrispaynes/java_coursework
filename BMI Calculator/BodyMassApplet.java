/*
  BodyMassApplet.java calculates the body mass index based on a person's height and weight. 
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class BodyMassApplet extends Applet implements ActionListener {
  Image logo;
  int inches, pounds;
  double kilograms, meters, bmi;

  // construct components
  Label companyLabel = new Label("BMI Index Calculator");
  Label heightLabel = new Label("Enter your height to the nearest inch: ");
    TextField heightField = new TextField(10);
  Label weightLabel = new Label("Enter your weight to the nearest inch: ");
    TextField weightField = new TextField(10);
  Button calcButton = new Button("Calculate");
  Label outputLabel = new Label("Click the Calculate button to see your BMI");

  // init loads the applet.
  public void init() {
    setForeground(Color.red);
    add(companyLabel);
    add(heightLabel);
    add(heightField);
    add(weightLabel);
    add(weightField);
    add(calcButton);
    calcButton.addActionListener(this);
    add(outputLabel);
    logo = getImage(getDocumentBase(), "logo.gif");
  }

  // actionPerformed calculates the BMI when the user clicks the calculate button.
  public void actionPerformed(ActionEvent e) {
    // Stores the text from AWT components.
    inches = Integer.parseInt(heightField.getText());
    pounds = Integer.parseInt(weightField.getText());
    
    meters = inches / 39.36;
    kilograms = pounds / 2.2;
    bmi = kilograms / Math.pow(meters,2);
   
    // setText assigns the output message to the outputLabel component.
    outputLabel.setText("YOUR BODY MASS INDEX IS " + Math.round(bmi) + ".");
  }

  // paint draws the image in the applet after it is initialized.
  public void paint(Graphics g) {
    g.drawImage(logo, 125, 160, this);
  }
}
