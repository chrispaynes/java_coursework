package collegecostexercise;

import java.util.Scanner;

public class CollegeCosts {

	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String studentName = getName();
		printTotal(studentName);
	}

	/**
	 * @param studentName the student's first and last name
	 */
	public static void printTotal(String studentName) {
		// TODO Auto-generated method stub
		boolean isOnCampus = onCampus();
		double rentalExpense = 0;
		double totalCollegeCost = 0;

		if (isOnCampus == true) {
			rentalExpense = getCostOfLiving();
		} else {
			rentalExpense = 0;
		}

		totalCollegeCost = rentalExpense + getSupplyCost() + getTuitionCost();

		System.out.println(studentName + "'s tuition cost is:");
		System.out.println("$" + totalCollegeCost);
	}

	/**
	 * @return the student's name
	 */
	public static String getName() {
		// TODO Auto-generated method stub
		System.out.println("Please Enter Your Name");
		String studentName = input.nextLine();
		return studentName;
	}

	/**
	 * @return true or false if student lives on campus
	 */
	public static boolean onCampus() {
		System.out.println("Are You Living On Campus?");
		String isOnCampus = input.nextLine();
		
		return isOnCampus.substring(0, 1).equalsIgnoreCase("y");
	}

	/**
	 * @return the total cost of school supplies.
	 */
	public static double getSupplyCost() {
		System.out.println("How Much Do Your Supplies Cost?");
		double supplyCost = input.nextDouble();
		
		return supplyCost;
	}

	/**
	 * @return the total tuition cost based on number of credit hours and cost per credit
	 */
	public static double getTuitionCost() {
		System.out.println("How Many Credit Hours Are You Anticipating?");
		int creditHours = input.nextInt();
		System.out.println("How Much Is Each Credit?");
		double creditCost = input.nextDouble();
		
		return creditHours * creditCost;
	}

	/**
	 * @return the cost of living for a specified timeframe
	 */
	public static double getCostOfLiving() {
		System.out.println("How Much Is Your Monthly Rent?");
		double rent = input.nextDouble();
		System.out.println("How Many Months Are Your Renting?");
		int rentalTerm = input.nextInt();

		return rent * rentalTerm;
	}

}
