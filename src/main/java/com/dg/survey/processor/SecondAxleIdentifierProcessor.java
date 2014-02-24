/**
 * 
 */
package com.dg.survey.processor;

import com.dg.survey.analyzer.VehicleRecord;
import com.dg.survey.model.LoadingContext;
import com.dg.survey.model.VehicleRecordEntity;
import com.dg.survey.util.TimeCalculator;

/**
 * @author johnver
 * 
 */
public class SecondAxleIdentifierProcessor implements VehicleRecordProcessor {

	private static double SECOND_AXLE_DISTANCE = 2.5;

	private static double ESTIMATED_SPEED_DISTANCE_KMS = 60; // 60
	private static double ESTIMATED_SPEED_DISTANCE_START_RANGE = (ESTIMATED_SPEED_DISTANCE_KMS - 55) * 1000; // 5kms
	private static double ESTIMATED_SPEED_DISTANCE_END_RANGE = (ESTIMATED_SPEED_DISTANCE_KMS + 55) * 1000; // 115kms

	private static double ESTIMATED_SPPED_TIME = 1 * 1000 * 60 * 60; // 1 hr
																		// converted
																		// to
																		// milliseconds.

	private static int ROUGH_TIME_TO_HIT_THE_SECOND_AXLE_START_RANGE = TimeCalculator
			.calculateTime(SECOND_AXLE_DISTANCE,
					ESTIMATED_SPEED_DISTANCE_START_RANGE, ESTIMATED_SPPED_TIME);

	private static int ROUGH_TIME_TO_HIT_THE_SECOND_AXLE_END_RANGE = TimeCalculator
			.calculateTime(SECOND_AXLE_DISTANCE,
					ESTIMATED_SPEED_DISTANCE_END_RANGE, ESTIMATED_SPPED_TIME);

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

		String axle = "1";

		VehicleRecord previous = vehicleRecord.getPrevious();
		boolean prefixMatch = false;
		while (previous != null && prefixMatch == false) {
			if (previous.getPrefix().equals(vehicleRecord.getPrefix())) {
				prefixMatch = true;
				final long timeDiff = vehicleRecord.getTimestamp().getTime()
						- previous.getTimestamp().getTime();
				if (timeDiff >= ROUGH_TIME_TO_HIT_THE_SECOND_AXLE_END_RANGE
						&& timeDiff <= ROUGH_TIME_TO_HIT_THE_SECOND_AXLE_START_RANGE) {
					axle = "2";

					String direction = "N";
					if (vehicleRecord.getPrevious() != null
							&& !vehicleRecord.getPrevious().getPrefix()
									.equals(vehicleRecord.getPrefix())) {
						direction = "S";
					}
					vehicleRecordEntity.setDirection(direction);

					final double speed = SECOND_AXLE_DISTANCE / timeDiff;
					vehicleRecordEntity.setSpeed(new Double(speed).toString());
				}
			} else {
				previous = previous.getPrevious();
			}
		}

		vehicleRecordEntity.setAxle(axle);

	}
}
