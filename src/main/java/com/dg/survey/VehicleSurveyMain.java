/**
 * 
 */
package com.dg.survey;

import java.io.InputStream;

import com.dg.survey.analyzer.VehicleSurveyAnalyzer;
import com.dg.survey.analyzer.VehicleSurveyAnalyzerImpl;

/**
 * @author johnver
 * 
 */
public class VehicleSurveyMain {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final VehicleSurveyAnalyzer analyzer = new VehicleSurveyAnalyzerImpl();
		final InputStream inputStream = analyzer.getClass().getClassLoader()
				.getResourceAsStream("sample.txt");
		try {
			analyzer.analyze(inputStream);
		} catch (final Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
