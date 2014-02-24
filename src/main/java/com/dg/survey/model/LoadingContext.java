/**
 * 
 */
package com.dg.survey.model;

/**
 * @author johnver
 * 
 */
public class LoadingContext {

	private Integer day = 0;

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return this.day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public synchronized void setDay(final Integer day) {
		this.day = day;
	}
}
