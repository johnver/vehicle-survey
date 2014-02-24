/**
 * 
 */
package com.dg.survey.analyzer;

import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author johnver
 * 
 */
public class VehicleSurveyAnalyzerImplTest {

	VehicleSurveyAnalyzerImplFixture vehicleSurveyAnalyzerFixture;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.vehicleSurveyAnalyzerFixture = new VehicleSurveyAnalyzerImplFixture();
	}

	/**
	 * Test method for
	 * {@link com.dg.survey.analyzer.VehicleSurveyAnalyzerImpl#analyze(java.io.InputStream)}
	 * .
	 */
	@Test
	public void should_read_the_file() {
		this.vehicleSurveyAnalyzerFixture.given_a_file();
		this.vehicleSurveyAnalyzerFixture.when_analyze_method_is_invoke();
		this.vehicleSurveyAnalyzerFixture.then_result_should_be_true();
	}

	@Test
	public void should_not_proceed() {
		this.vehicleSurveyAnalyzerFixture.given_an_invalid_file();
		this.vehicleSurveyAnalyzerFixture.when_analyze_method_is_invoke();
		this.vehicleSurveyAnalyzerFixture.then_result_should_be_false();
	}

	class VehicleSurveyAnalyzerImplFixture {

		private VehicleSurveyAnalyzer vehicleSurveyAnalyzer;

		private InputStream inputStream;

		private boolean result;

		VehicleSurveyAnalyzerImplFixture() {

		}

		public void given_a_file() {
			this.inputStream = this.getClass().getClassLoader()
					.getResourceAsStream("sample.txt");

		}

		public void given_an_invalid_file() {
			this.inputStream = this.getClass().getClassLoader()
					.getResourceAsStream("sample1.txt");

		}

		public void when_analyze_method_is_invoke() {
			this.vehicleSurveyAnalyzer = new VehicleSurveyAnalyzerImpl();

			try {
				this.vehicleSurveyAnalyzer.analyze(this.inputStream);
				this.result = true;
			} catch (final Exception e) {
				this.result = false;
			}
		}

		public void then_result_should_be_true() {
			Assert.assertTrue(this.result == true);
		}

		public void then_result_should_be_false() {
			Assert.assertTrue(this.result == false);
		}
	}

}
