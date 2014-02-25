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
public class SecondAxleIdentifierProcessor implements VehicleRecordProcessor {

	private static final Logger logger = Logger
			.getLogger(SecondAxleIdentifierProcessor.class.getName());

	private final VehicleRecordProcessor successor;

	public SecondAxleIdentifierProcessor() {
		this.successor = new DirectionIdentifierProcessor();
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

		String axle = AppConstants.FIRST_AXLE;

		VehicleRecord previous = vehicleRecord.getPrevious();
		boolean prefixMatch = false;
		while (previous != null && prefixMatch == false) {
			if (previous.getPrefix().equals(vehicleRecord.getPrefix())) {
				prefixMatch = true;
				final long timeDiff = vehicleRecord.getTimestamp().getTime()
						- previous.getTimestamp().getTime();
				if (timeDiff >= AppConstants.ROUGH_TIME_TO_HIT_THE_SECOND_AXLE_END_RANGE
						&& timeDiff <= AppConstants.ROUGH_TIME_TO_HIT_THE_SECOND_AXLE_START_RANGE) {
					axle = AppConstants.SECOND_AXLE;

					final double speed = AppConstants.SECOND_AXLE_DISTANCE
							/ timeDiff;
					final double rounded = Math.round(speed * 1000.0) / 1000.0;
					vehicleRecordEntity
							.setSpeed(new Double(rounded).toString());
					vehicleRecordEntity.setTimeDifference("" + timeDiff);
				}
			} else {
				previous = previous.getPrevious();
			}
		}

		vehicleRecordEntity.setAxle(axle);

		if (this.successor != null) {
			this.successor.process(vehicleRecord, vehicleRecordEntity, context);
		} else {
			logger.info("End of the chain");
		}

	}
}
