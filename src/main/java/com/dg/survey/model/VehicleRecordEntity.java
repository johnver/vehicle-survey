/**
 * 
 */
package com.dg.survey.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.dg.survey.analyzer.VehicleRecord;
import com.dg.survey.util.AppConstants;

/**
 * @author johnver
 * 
 */
public class VehicleRecordEntity {

	private String rawData;
	private String day;
	private String direction;
	private String axle;
	private String speed;
	private String timestamp;

	private static String TIME_FORMAT = "HH:mm:ss.SSS";

	public VehicleRecordEntity() {

	}

	public VehicleRecordEntity(final VehicleRecord vehicleRecord) {
		this.rawData = vehicleRecord.getRecord();
		this.direction = vehicleRecord.getPrefix();
		final DateFormat sf = new SimpleDateFormat(TIME_FORMAT);
		this.timestamp = sf.format(vehicleRecord.getTimestamp());
	}

	/**
	 * @return the rawData
	 */
	public String getRawData() {
		return this.rawData;
	}

	/**
	 * @param rawData
	 *            the rawData to set
	 */
	public void setRawData(final String rawData) {
		this.rawData = rawData;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return this.day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(final String day) {
		this.day = day;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return this.direction;
	}

	/**
	 * @param direction
	 *            the direction to set
	 */
	public void setDirection(final String direction) {
		this.direction = direction;
	}

	/**
	 * @return the axle
	 */
	public String getAxle() {
		return this.axle;
	}

	/**
	 * @param axle
	 *            the axle to set
	 */
	public void setAxle(final String axle) {
		this.axle = axle;
	}

	/**
	 * @return the speed
	 */
	public String getSpeed() {
		return this.speed;
	}

	/**
	 * @param speed
	 *            the speed to set
	 */
	public void setSpeed(final String speed) {
		this.speed = speed;
	}

	public String getPersistentString() {
		final StringBuilder builder = new StringBuilder();
		builder.append(this.rawData);
		builder.append(AppConstants.DELIMITER);
		builder.append(this.day);
		builder.append(AppConstants.DELIMITER);
		builder.append(this.axle);
		builder.append(AppConstants.DELIMITER);
		builder.append(this.timestamp);
		builder.append(AppConstants.DELIMITER);
		builder.append(this.direction);
		builder.append(AppConstants.DELIMITER);
		builder.append(this.speed);

		return builder.toString();
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return this.timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(final String timestamp) {
		this.timestamp = timestamp;
	}

}
