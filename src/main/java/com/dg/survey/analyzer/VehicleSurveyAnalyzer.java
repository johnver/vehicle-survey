/**
 * 
 */
package com.dg.survey.analyzer;

import java.io.InputStream;

/**
 * @author johnver
 * 
 */
public interface VehicleSurveyAnalyzer {

	public void analyze(InputStream data) throws Exception;
}
