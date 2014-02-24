/**
 * 
 */
package com.dg.survey.report;

/**
 * @author johnver
 * 
 */
public class TotalCountReportParameter {

	private int sessionType;
	private int periodType;

	/**
	 * @return the sessionType
	 */
	public int getSessionType() {
		return this.sessionType;
	}

	/**
	 * @param sessionType
	 *            the sessionType to set
	 */
	public void setSessionType(final int sessionType) {
		this.sessionType = sessionType;
	}

	/**
	 * @return the periodType
	 */
	public int getPeriodType() {
		return this.periodType;
	}

	/**
	 * @param periodType
	 *            the periodType to set
	 */
	public void setPeriodType(final int periodType) {
		this.periodType = periodType;
	}

}
