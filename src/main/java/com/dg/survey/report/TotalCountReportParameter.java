/**
 * 
 */
package com.dg.survey.report;

/**
 * @author johnver
 * 
 */
public class TotalCountReportParameter {

	private int reportType;
	private int sessionType;
	private int periodType;
	private int directionType;

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

	/**
	 * @return the reportType
	 */
	public int getReportType() {
		return this.reportType;
	}

	/**
	 * @param reportType
	 *            the reportType to set
	 */
	public void setReportType(final int reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the directionType
	 */
	public int getDirectionType() {
		return this.directionType;
	}

	/**
	 * @param directionType
	 *            the directionType to set
	 */
	public void setDirectionType(final int directionType) {
		this.directionType = directionType;
	}

}
