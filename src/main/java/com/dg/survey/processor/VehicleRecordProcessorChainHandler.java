/**
 * 
 */
package com.dg.survey.processor;

import com.dg.survey.analyzer.VehicleRecord;
import com.dg.survey.model.LoadingContext;
import com.dg.survey.model.VehicleRecordEntity;
import com.dg.survey.persistence.VehicleRecordManager;
import com.dg.survey.persistence.VehicleRecordManagerImpl;

/**
 * @author johnver
 * 
 */
public class VehicleRecordProcessorChainHandler implements
		VehicleRecordProcessor {

	private VehicleRecordManager vehicleSurveyManager;

	private final VehicleRecordProcessor successor;

	public VehicleRecordProcessorChainHandler() {

		this.successor = new DayIdentifierProcessor();
		this.vehicleSurveyManager = new VehicleRecordManagerImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dg.survey.processor.VehicleRecordProcessor#process(java.lang.String)
	 */
	public void process(final VehicleRecord vehicleRecord,
			final LoadingContext context) throws Exception {

		final VehicleRecordEntity vehicleRecordEntity = new VehicleRecordEntity(
				vehicleRecord);
		this.process(vehicleRecord, vehicleRecordEntity, context);

		this.vehicleSurveyManager.create(vehicleRecordEntity);

	}

	/**
	 * @return the vehicleSurveyManager
	 */
	public VehicleRecordManager getVehicleSurveyManager() {
		return this.vehicleSurveyManager;
	}

	/**
	 * @param vehicleSurveyManager
	 *            the vehicleSurveyManager to set
	 */
	public void setVehicleSurveyManager(
			final VehicleRecordManager vehicleSurveyManager) {
		this.vehicleSurveyManager = vehicleSurveyManager;
	}

	public void process(final VehicleRecord vehicleRecord,
			final VehicleRecordEntity vehicleRecordEntity,
			final LoadingContext context) throws Exception {

		this.successor.process(vehicleRecord, vehicleRecordEntity, context);

	}

}
