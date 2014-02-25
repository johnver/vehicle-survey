/**
 * 
 */
package com.dg.survey.persistence;

import java.sql.Timestamp;

import com.dg.survey.model.TimeRange;
import com.dg.survey.model.VehicleRecordEntity;

/**
 * @author johnver
 * 
 */
public interface VehicleRecordManager {

	public void create(VehicleRecordEntity vehicleItem) throws Exception;

	public void deleteDatabase() throws Exception;

	public void createDatabase() throws Exception;

	public int retrieveTotalCountPerDirection(String direction)
			throws Exception;

	public int retrieveTotalCountPerSessionDirection(String session,
			String direction) throws Exception;

	public double retrieveAverageSpeedPerSessionDirection(String session,
			String direction) throws Exception;

	public int retrieveTotalCountPerSessionDirection(final String session,
			final String direction, final TimeRange timerange) throws Exception;

	public Timestamp retrieveSessionEndTime(String session) throws Exception;

	public double retrieveAverageSpeedPerSessionDirection(final String session,
			final String direction, final TimeRange timerange) throws Exception;

	public double retrieveAverageDistancePerSessionDirection(String session,
			String direction, TimeRange timerange) throws Exception;

	public double retrieveAverageDistancePerSessionDirection(String session,
			String direction) throws Exception;

}
