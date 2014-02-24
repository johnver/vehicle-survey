/**
 * 
 */
package com.dg.survey.processor;

import java.util.logging.Logger;

import com.dg.survey.analyzer.VehicleRecord;
import com.dg.survey.model.LoadingContext;
import com.dg.survey.model.VehicleRecordEntity;

/**
 * @author johnver
 * 
 */
public class DayIdentifierProcessor implements VehicleRecordProcessor {

	private static final Logger logger = Logger
			.getLogger(DayIdentifierProcessor.class.getName());

	private final VehicleRecordProcessor successor;

	public DayIdentifierProcessor() {
		this.successor = new SecondAxleIdentifierProcessor();
	}

	public void process(final VehicleRecord vehicleRecord,
			final VehicleRecordEntity vehicleRecordEntity,
			final LoadingContext context) throws Exception {

		final VehicleRecord previous = vehicleRecord.getPrevious();
		boolean prefixMatch = false;
		while (previous != null && prefixMatch == false) {
			prefixMatch = true;
			final long timeDiff = vehicleRecord.getTimestamp().getTime()
					- previous.getTimestamp().getTime();
			if (timeDiff < 0) {
				// Means that the clock has reset.
				final Integer nextDay = context.getDay() + 1;
				context.setDay(nextDay);
			}
		}

		vehicleRecordEntity.setDay(context.getDay().toString());

		if (this.successor != null) {
			this.successor.process(vehicleRecord, vehicleRecordEntity, context);
		} else {
			logger.info("Successor is null.");
		}
	}
}
