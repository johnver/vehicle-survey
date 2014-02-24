/**
 * 
 */
package com.dg.survey.analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.dg.survey.model.LoadingContext;
import com.dg.survey.persistence.VehicleRecordManager;
import com.dg.survey.persistence.VehicleRecordManagerImpl;
import com.dg.survey.processor.VehicleRecordProcessor;
import com.dg.survey.processor.VehicleRecordProcessorChainHandler;

/**
 * @author johnver
 * 
 */
public class VehicleSurveyAnalyzerImpl implements VehicleSurveyAnalyzer {

	private final VehicleRecordProcessorChainHandler processor;

	private final VehicleRecordManager manager;

	public VehicleSurveyAnalyzerImpl() {
		this.processor = new VehicleRecordProcessorChainHandler();
		this.manager = new VehicleRecordManagerImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dg.survey.analyzer.VehicleSurveyAnalyzer#analyze(java.io.InputStream)
	 */
	public void analyze(final InputStream data) throws Exception {

		this.manager.deleteDatabase();
		this.manager.createDatabase();

		BufferedReader br = null;

		try {

			System.out.println("Analyzing the data.. Please wait..");
			String sCurrentLine;
			final InputStreamReader inputStreamReader = new InputStreamReader(
					data);
			br = new BufferedReader(inputStreamReader);

			final LoadingContext context = new LoadingContext();
			VehicleRecord previous = null;
			while ((sCurrentLine = br.readLine()) != null) {
				final VehicleRecord vehicleRecord = new VehicleRecord(previous,
						sCurrentLine);
				this.processor.process(vehicleRecord, context);
				previous = vehicleRecord;

			}
			System.out.println("Done analyzing the data...");

		} catch (final Exception e) {
			throw e;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @return the processor
	 */
	public VehicleRecordProcessor getProcessor() {
		return this.processor;
	}

}
