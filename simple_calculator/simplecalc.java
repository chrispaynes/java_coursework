// Simple calculator that allows a user to input two operands and a operator
// Outputs the result of the math operation

import java.util.Scanner;

public class simplecalc {
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    // declares arithmetic operands and arithmetic result variables
    double number1, number2, answer;
    // character used to determine arithmetic operator
    char operator;

    // scans user input to set number1 variable
    System.out.println("Please enter the first number: ");
    number1 = scan.nextDouble();
    
    // scans user input to set number2 variable
    System.out.println("Please enter the second number: ");
    number2 = scan.nextDouble();

    // scans user input to set the math operator to the first character
    System.out.println("Please enter the operator: ");
    operator = scan.next().charAt(0); 

    // uses user input for the operator variable to choose the math operation
    switch(operator) {
      case '+':
        answer = number1 + number2;
        break;
      case '-':
        answer = number1 - number2;
        break;
      case '*':
        answer = number1 * number2;
        break;
      case '/':
        answer = (number1 / number2);
        break;
      case '%':
        answer = number1 % number2;
        break;
      default:
        answer = number1 + number2;
    }

    // prints result to system output
    // rounds the resulting float to two digits
    System.out.printf("%.2f%n", answer);
  }
}