/**
 * 
 */
package com.dg.survey.model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.dg.survey.util.AppConstants;

/**
 * @author johnver
 * 
 */
public class TimeRange {

	private Timestamp startTime;
	private Timestamp endTime;

	public TimeRange(final Timestamp startTime, final Timestamp endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * @return the startTime
	 */
	public Timestamp getStartTime() {
		return this.startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(final Timestamp startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Timestamp getEndTime() {
		return this.endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(final Timestamp endTime) {
		this.endTime = endTime;
	}

	public String getDay() {
		final DateFormat format = new SimpleDateFormat("d");
		final String day = format.format(this.startTime);

		return day;

	}

	public String getDisplay() {
		final DateFormat format = new SimpleDateFormat(
				AppConstants.TIMEONLY_FORMAT);
		final String startString = format.format(this.startTime);
		final String endString = format.format(this.endTime);

		return startString + "-" + endString;

	}

	public String getRegEx() {

		return "(.*)";
	}

	public boolean isInRange(final Timestamp aDate) {
		boolean ret = false;

		if (aDate.getTime() >= this.getStartTime().getTime()
				&& aDate.getTime() < this.getEndTime().getTime()) {
			ret = true;
		}
		return ret;
	}

}
