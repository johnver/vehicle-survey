/**
 * 
 */
package com.dg.survey.processor;

import java.util.logging.Logger;

import com.dg.survey.analyzer.VehicleRecord;
import com.dg.survey.model.LoadingContext;
import com.dg.survey.model.VehicleRecordEntity;
import com.dg.survey.util.AppConstants;

/**
 * @author johnver
 * 
 */
public class DirectionIdentifierProcessor implements VehicleRecordProcessor {

	private static final Logger logger = Logger
			.getLogger(DayIdentifierProcessor.class.getName());

	private final VehicleRecordProcessor successor;

	public DirectionIdentifierProcessor() {
		this.successor = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dg.survey.processor.VehicleRecordProcessor#process(com.dg.survey.
	 * analyzer.VehicleRecord, com.dg.survey.model.VehicleRecordEntity)
	 */
	public void process(final VehicleRecord vehicleRecord,
			final VehicleRecordEntity vehicleRecordEntity,
			final LoadingContext context) throws Exception {

		String direction = vehicleRecord.getPrefix();

		if (AppConstants.SECOND_AXLE.equalsIgnoreCase(vehicleRecordEntity
				.getAxle())) {

			if (vehicleRecord.getPrevious() != null
					&& !vehicleRecord.getPrevious().getPrefix()
							.equals(vehicleRecord.getPrefix())) {
				direction = AppConstants.SOUTHBOUND;
			} else {
				direction = AppConstants.NORTHBOUND;
			}

		}

		vehicleRecordEntity.setDirection(direction);

		if (this.successor != null) {
			this.successor.process(vehicleRecord, vehicleRecordEntity, context);
		} else {
			// logger.info("End of the chain.");
		}

	}
}
