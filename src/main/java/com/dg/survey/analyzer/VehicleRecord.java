/**
 * 
 */
package com.dg.survey.analyzer;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.dg.survey.util.AppConstants;

/**
 * @author johnver
 * 
 */
public final class VehicleRecord {

	private VehicleRecord previous;

	private final String record;
	private final String prefix;
	private final Timestamp timestamp;

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			AppConstants.TIMESTAMP_FORMAT);

	public VehicleRecord(final VehicleRecord previous, final String record) {
		this.validate(record);
		this.previous = previous;
		this.record = record;
		this.prefix = this.parsePrefix(record);
		this.timestamp = this.parseTimestamp(record);
	}

	private void validate(final String record) {
		if (record == null || "".equals(record)) {
			throw new IllegalArgumentException();
		}
	}

	private String parsePrefix(final String record) {
		return record.substring(0, 1);

	}

	private Timestamp parseTimestamp(final String record) {
		final String millisecondStr = record.substring(1, record.length());
		final Long millisecond = new Long(millisecondStr);
		final Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, 1970);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		final Long midnightMillis = calendar.getTimeInMillis();
		final Timestamp time = new Timestamp(midnightMillis + millisecond);
		return time;
	}

	/**
	 * @return the previous
	 */
	public VehicleRecord getPrevious() {
		return this.previous;
	}

	/**
	 * @param previous
	 *            the previous to set
	 */
	public void setPrevious(final VehicleRecord previous) {
		this.previous = previous;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return this.prefix;
	}

	/**
	 * @return the record
	 */
	public String getRecord() {
		return this.record;
	}

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.prefix == null) ? 0 : this.prefix.hashCode());
		result = prime * result
				+ ((this.previous == null) ? 0 : this.previous.hashCode());
		result = prime * result
				+ ((this.record == null) ? 0 : this.record.hashCode());
		result = prime * result
				+ ((this.timestamp == null) ? 0 : this.timestamp.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final VehicleRecord other = (VehicleRecord) obj;
		if (this.prefix == null) {
			if (other.prefix != null) {
				return false;
			}
		} else if (!this.prefix.equals(other.prefix)) {
			return false;
		}
		if (this.previous == null) {
			if (other.previous != null) {
				return false;
			}
		} else if (!this.previous.equals(other.previous)) {
			return false;
		}
		if (this.record == null) {
			if (other.record != null) {
				return false;
			}
		} else if (!this.record.equals(other.record)) {
			return false;
		}
		if (this.timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!this.timestamp.equals(other.timestamp)) {
			return false;
		}
		return true;
	}

}
