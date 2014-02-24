package com.dg.survey.util;

/**
 * @author johnver
 * 
 */
public class AppConstants {

	public static final String NORTHBOUND = "N";
	public static final String SOUTHBOUND = "S";
	public static final String SECOND_AXLE = "2";
	public static final String FIRST_AXLE = "1";
	public static double SECOND_AXLE_DISTANCE = 2.5;
	public static double ESTIMATED_SPEED_DISTANCE_KMS = 60; // 60
	public static double ESTIMATED_SPEED_DISTANCE_START_RANGE = (ESTIMATED_SPEED_DISTANCE_KMS - 55) * 1000; // 5kms
	public static double ESTIMATED_SPEED_DISTANCE_END_RANGE = (ESTIMATED_SPEED_DISTANCE_KMS + 55) * 1000; // 115kms
	public static double ESTIMATED_SPPED_TIME = 1 * 1000 * 60 * 60; // 1 hr
	// converted
	// to
	// milliseconds.
	public static int ROUGH_TIME_TO_HIT_THE_SECOND_AXLE_START_RANGE = TimeCalculator
			.calculateTime(SECOND_AXLE_DISTANCE,
					ESTIMATED_SPEED_DISTANCE_START_RANGE, ESTIMATED_SPPED_TIME);
	public static int ROUGH_TIME_TO_HIT_THE_SECOND_AXLE_END_RANGE = TimeCalculator
			.calculateTime(SECOND_AXLE_DISTANCE,
					ESTIMATED_SPEED_DISTANCE_END_RANGE, ESTIMATED_SPPED_TIME);
	public static final String DELIMITER = "|";
	public static final String ESCAPE_CHAR = "\\";
	public static String TIMESTAMP_FORMAT = "HH:mm:ss.SSS";

}
