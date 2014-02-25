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

	public static List<TimeRange> defineHourRanges(final Timestamp endTime,
			final int interval) {
		final List<TimeRange> hourRanges = new LinkedList<TimeRange>();
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		for (long i = calendar.getTimeInMillis(); i <= endTime.getTime(); i = calendar
				.getTimeInMillis()) {
			final Timestamp start = new Timestamp(calendar.getTimeInMillis());
			calendar.add(Calendar.HOUR_OF_DAY, interval);
			final Timestamp end = new Timestamp(calendar.getTimeInMillis());
			hourRanges.add(new TimeRange(start, end));
		}

		return hourRanges;
	}

	public static List<TimeRange> defineMinuteRanges(final Timestamp endTime,
			final int interval) {
		final List<TimeRange> minuteRanges = new LinkedList<TimeRange>();
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		for (long i = calendar.getTimeInMillis(); i <= endTime.getTime(); i = calendar
				.getTimeInMillis()) {
			final Timestamp start = new Timestamp(calendar.getTimeInMillis());
			calendar.add(Calendar.MINUTE, interval);
			final Timestamp end = new Timestamp(calendar.getTimeInMillis());
			minuteRanges.add(new TimeRange(start, end));
		}

		return minuteRanges;
	}

}
