/**
 * 
 */
package com.dg.survey.processor;

import com.dg.survey.analyzer.VehicleRecord;
import com.dg.survey.model.LoadingContext;
import com.dg.survey.model.VehicleRecordEntity;

/**
 * @author johnver
 * 
 */
public interface VehicleRecordProcessor {

	public void process(VehicleRecord vehicleRecord,
			VehicleRecordEntity vehicleRecordEntity, LoadingContext context)
			throws Exception;
}
