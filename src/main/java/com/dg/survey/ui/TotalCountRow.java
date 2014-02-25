/**
 * 
 */
package com.dg.survey.ui;

/**
 * @author johnver
 * 
 */
public class TotalCountRow {

	private String session;
	private String displayName;
	private Integer southBound;
	private Integer northBound;

	public TotalCountRow(final String session, final String displayName,
			final Integer southBound, final Integer northBound) {
		this.session = session;
		this.displayName = displayName;
		this.southBound = southBound;
		this.northBound = northBound;
	}

	/**
	 * @return the session
	 */
	public String getSession() {
		return this.session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(final String session) {
		this.session = session;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the southBound
	 */
	public Integer getSouthBound() {
		return this.southBound;
	}

	/**
	 * @param southBound
	 *            the southBound to set
	 */
	public void setSouthBound(final Integer southBound) {
		this.southBound = southBound;
	}

	/**
	 * @return the northBound
	 */
	public Integer getNorthBound() {
		return this.northBound;
	}

	/**
	 * @param northBound
	 *            the northBound to set
	 */
	public void setNorthBound(final Integer northBound) {
		this.northBound = northBound;
	}

}
