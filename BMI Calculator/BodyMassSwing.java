/*
  BodyMassSwing.java calculates the body mass index based on a person's height and weight:w 
 */

import javax.swing.JOptionPane;

public class BodyMassSwing {

  public static void main(String[] args) {
    String height, weight;
    int inches, pounds;
    double kilograms, meters, bmi;

    // Prompts user and stores input.
    System.out.println("BODY MASS INDEX CALCULATOR");
    height = JOptionPane.showInputDialog(null, "Enter your height to the nearest inch: ");
      inches = Integer.parseInt(height);
    weight = JOptionPane.showInputDialog(null, "Enter your weight to the nearest pound: ");
      pounds = Integer.parseInt(weight);

    // Calculates BMI
    meters = inches / 39.36;
    kilograms = pounds / 2.2;
    bmi = kilograms / Math.pow(meters, 2);

    // Outputs
    JOptionPane.showMessageDialog(null, "YOUR BODY MASS INDEX IS " + Math.round(bmi) + ".",
        "Body Mass Index Calculator", JOptionPane.PLAIN_MESSAGE);
    

  System.exit(0);
  }
}
