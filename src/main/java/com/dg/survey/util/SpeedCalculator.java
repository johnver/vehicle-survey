/**
 * 
 */
package com.dg.survey.util;

/**
 * @author johnver
 * 
 */
public class SpeedCalculator {

	public static int convertMeterPerMillisecondToKph(final double speed) {
		final double kphSpeed = speed * (1 * 1000 * 60 * 60 / 1000);

		return (int) kphSpeed;
	}

	public static double convertMeterToKm(final double meter) {
		final double kilometer = meter / 1000;

		return Math.round(kilometer * 100.0) / 100.0;
	}

	public static int calculateDistance(final double speed, final long timeDiff) {
		final double distance = speed * timeDiff;

		return (int) distance;
	}

}
