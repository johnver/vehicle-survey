/**
 * 
 */
package com.dg.survey.util;

/**
 * @author johnver
 * 
 */
public class TimeCalculator {

	public static int calculateTime(final double distance, final double speed) {
		final double time = distance / speed;
		return (int) time;
	}

	public static int calculateTime(final double distance,
			final double speedDivisor, final double speedDividend) {
		final double time = distance * (speedDividend / speedDivisor);
		return (int) time;
	}

}
