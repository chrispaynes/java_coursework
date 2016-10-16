/*
	Java application that displays today's date.
	Display the date in the following format: week day, month, date, and year.
	More specifically display the date in the following manner:
	Tuesday, October 12, 2010
 */

import java.util.Calendar;
import java.util.Locale;

public class DateDisplay {

	/*
	  	Display the date in the following format: week day, month, date, and year.
	  	
		@param locale		the default locale for this instance of the Java Virtual Machine.
		@param currentDate	a calendar using the default time zone and locale.
		@param day_name		a string representation of the week day.
		@param month_name	a string representation of the month.
		@param day_number	a integer value for the day.
		@param year_number	a integer value for the year.
		@param full_date	a string value for week day, month, date, and year.
	*/
	
	public static void main(String[] args) {
		Locale locale = Locale.getDefault();
		Calendar currentDate = Calendar.getInstance();
		
		String day_name = currentDate.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
		String month_name = currentDate.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);
		int day_number = currentDate.get(Calendar.DAY_OF_MONTH);
		int year_number = currentDate.get(Calendar.YEAR);
		
		String full_date = day_name + ", " + month_name + " " + day_number + ", " + year_number;

		System.out.println(full_date);
	}

}
