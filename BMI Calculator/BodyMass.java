/*
  BodyMass.java calculates the body mass index based on a person's height and weight.
 
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class BodyMass {

  public static void main(String[] args) throws IOException {
    String height, weight;
    int inches, pounds;
    double kilograms, meters, bmi;
    Scanner scannerIn = new Scanner(System.in);

    // dataIn stores input stream text.
    // BufferedReader reads text from a character stream.
    // InputStreamReader decodes the byte stream to character stream.
    BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));

    // Prompts user and stores input.
    System.out.println("BODY MASS INDEX CALCULATOR");
    System.out.println();
    System.out.print(" Enter your height rounded to the nearest inch:   ");
      height = dataIn.readLine();
      inches = Integer.parseInt(height);
    System.out.print(" Enter your weight rounded to the nearest pound:  ");
      weight = dataIn.readLine();
      pounds = Integer.parseInt(weight); 

    // Calculates BMI
    meters = inches / 39.36;
    kilograms = pounds / 2.2;
    bmi = kilograms / Math.pow(meters, 2);

    // Outputs
    System.out.println();
    System.out.println("Your Body Mass Index is " + Math.round(bmi) + ".");
    System.out.println();
  }
}
