package com.flipcard.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeDay {
	public static String getDateTimeDay() {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		return localDate.getDayOfWeek()+" "+localDate+" "+localTime;
	
	}
	
	public static String getDateTime() {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		return localDate+" "+localTime;
	
	}
}
