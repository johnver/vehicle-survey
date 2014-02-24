/**
 * 
 */
package com.dg.survey.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import com.dg.survey.model.TimeRange;

/**
 * @author johnver
 * 
 */
public class TimeRangeUtil {

	public static List<TimeRange> HALF_DAY;
	public static List<TimeRange> PER_HOUR;
	public static List<TimeRange> PER_HALF_HOUR;
	public static List<TimeRange> PER_20_MINUTES;
	public static List<TimeRange> PER_15_MINUTES;
	static {
		defineHalfDay();
		definePerHour();
		definePerHalfHour();
		definePer20Mins();
		definePer15Mins();
	}

	private static void defineHalfDay() {
		HALF_DAY = new LinkedList<TimeRange>();
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		final Timestamp start = new Timestamp(calendar.getTimeInMillis());
		calendar.add(Calendar.HOUR_OF_DAY, 12);
		final Timestamp end = new Timestamp(calendar.getTimeInMillis());
		HALF_DAY.add(new TimeRange(start, end));

		final Timestamp afterNoonStart = new Timestamp(
				calendar.getTimeInMillis());
		calendar.add(Calendar.HOUR_OF_DAY, 12);
		final Timestamp afterNoonEnd = new Timestamp(calendar.getTimeInMillis());
		HALF_DAY.add(new TimeRange(afterNoonStart, afterNoonEnd));
	}

	private static void definePerHour() {
		PER_HOUR = new LinkedList<TimeRange>();
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		for (int i = 1; i <= 24; i++) {
			final Timestamp start = new Timestamp(calendar.getTimeInMillis());
			calendar.add(Calendar.HOUR_OF_DAY, 1);
			final Timestamp end = new Timestamp(calendar.getTimeInMillis());
			PER_HOUR.add(new TimeRange(start, end));
		}

	}

	private static void definePerHalfHour() {
		PER_HALF_HOUR = new LinkedList<TimeRange>();
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		final int minutePerDay = (24 * 60) / 30;
		for (int i = 1; i <= minutePerDay; i++) {
			final Timestamp start = new Timestamp(calendar.getTimeInMillis());
			calendar.add(Calendar.MINUTE, 30);
			final Timestamp end = new Timestamp(calendar.getTimeInMillis());
			PER_HALF_HOUR.add(new TimeRange(start, end));
		}

	}

	private static void definePer20Mins() {
		PER_20_MINUTES = new LinkedList<TimeRange>();
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		final int minutePerDay = (24 * 60) / 20;
		for (int i = 1; i <= minutePerDay; i++) {
			final Timestamp start = new Timestamp(calendar.getTimeInMillis());
			calendar.add(Calendar.MINUTE, 20);
			final Timestamp end = new Timestamp(calendar.getTimeInMillis());
			PER_20_MINUTES.add(new TimeRange(start, end));
		}

	}

	private static void definePer15Mins() {
		PER_15_MINUTES = new LinkedList<TimeRange>();
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		final int minutePerDay = (24 * 60) / 15;
		for (int i = 1; i <= minutePerDay; i++) {
			final Timestamp start = new Timestamp(calendar.getTimeInMillis());
			calendar.add(Calendar.MINUTE, 15);
			final Timestamp end = new Timestamp(calendar.getTimeInMillis());
			PER_15_MINUTES.add(new TimeRange(start, end));
		}

	}
}
