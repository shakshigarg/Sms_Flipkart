package com.flipcard.utils;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * The utils class used to get date,time and day
 */
public class DateTimeDay {
	
	// return string that contains day of week, date, time
	public static String getDateTimeDay() {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		return localDate.getDayOfWeek()+" "+localDate+" "+localTime;
	
	}
	
	// return string that contains date and time
	public static String getDateTime() {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		return localDate+" "+localTime;
	
	}
}
