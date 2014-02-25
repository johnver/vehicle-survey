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

}
