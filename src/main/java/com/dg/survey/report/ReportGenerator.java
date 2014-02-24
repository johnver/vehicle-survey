/**
 * 
 */
package com.dg.survey.report;

import com.dg.survey.ui.Table;

/**
 * @author johnver
 * 
 */
public interface ReportGenerator<T> {

	public Table generate(T parameter);
}
