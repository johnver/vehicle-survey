/**
 * 
 */
package com.dg.survey.manager;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.dg.survey.model.TimeRange;
import com.dg.survey.persistence.VehicleRecordManagerImpl;
import com.dg.survey.util.AppConstants;
import com.dg.survey.util.TimeRangeUtil;

/**
 * @author johnver
 * 
 */
public class VehicleRecordManagerImplTest {

	private VehicleRecordManagerImplTestFixture testFixture;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testFixture = new VehicleRecordManagerImplTestFixture();
	}

	/**
	 * Test method for
	 * {@link com.dg.survey.persistence.VehicleRecordManagerImpl#retrieveTotalCountPerDirection(java.lang.String)}
	 * .
	 */
	@Test
	public void should_retrieve_total_count_per_direction() {
		this.testFixture.given_a_direction();
		this.testFixture.when_retrieve_total_count_per_direction_is_invoked();
		this.testFixture.then_actual_count_and_expected_count_should_match();
	}

	@Test
	public void should_retrieve_total_count_with_timestamp() {
		this.testFixture.given_a_timerange();
		this.testFixture
				.when_retrieve_total_count_per_direction_with_time_is_invoked();
		this.testFixture.then_actual_count_and_expected_count_should_match();
	}

	@Test
	public void should_retrieve_the_session_end_time() {
		this.testFixture.given_the_first_sesion();
		this.testFixture.when_retrieve_session_end_time_is_invoked();
		this.testFixture
				.then_actual_timestamp_and_expected_timestamp_should_match();
	}

	@Test
	public void should_retrieve_the_session_end_time_for_second_session() {
		this.testFixture.given_the_second_sesion();
		this.testFixture.when_retrieve_session_end_time_is_invoked();
		this.testFixture
				.then_actual_timestamp_and_expected_timestamp_should_match();
	}

	@Test
	public void should_retrieve_average_speed_with_timestamp() {
		this.testFixture.given_a_direction();
		this.testFixture
				.when_retrieve_average_speed_per_direction_with_time_is_invoked();
		this.testFixture.then_actual_speed_and_expected_speed_should_match();
	}

	class VehicleRecordManagerImplTestFixture {

		private VehicleRecordManagerImpl manager;

		private int expectedCount;
		private int actualCount;
		private String direction;
		String session;
		private TimeRange timeRange;
		private Timestamp expectedEndTime;
		private Timestamp actualEndTime;
		private double actualAverageSpeed;
		private double expectedAverageSpeed;;

		private static final String TEST_DB_FILE = "app-data/test-db.txt";

		public void given_a_direction() {
			this.direction = "S";
			this.expectedCount = 6;
			this.session = "0";
			this.expectedAverageSpeed = 61.0;
		}

		public void given_a_timerange() {
			this.direction = "S";
			this.expectedCount = 5;
			this.session = "0";

			final DateFormat df = new SimpleDateFormat(
					AppConstants.TIMESTAMP_FORMAT);

			try {
				final Date date = df.parse("01 12:35:46.349");
				this.expectedEndTime = new Timestamp(date.getTime());
			} catch (final ParseException e) {
				Assert.fail("should not throw an exception");
			}

			this.timeRange = TimeRangeUtil.defineHourRanges(
					this.expectedEndTime, 12).get(0);
		}

		public void given_the_first_sesion() {
			this.session = "0";

			final DateFormat df = new SimpleDateFormat(
					AppConstants.TIMESTAMP_FORMAT);
			Date date;
			try {
				date = df.parse("01 00:35:46.349");
				this.expectedEndTime = new Timestamp(date.getTime());
			} catch (final ParseException e) {
				Assert.fail("should not throw an exception");
			}

		}

		public void given_the_second_sesion() {
			this.session = "1";

			final DateFormat df = new SimpleDateFormat(
					AppConstants.TIMESTAMP_FORMAT);
			Date date;
			try {
				date = df.parse("01 00:29:42.664");
				this.expectedEndTime = new Timestamp(date.getTime());
			} catch (final ParseException e) {
				Assert.fail("should not throw an exception");
			}

		}

		public void when_retrieve_total_count_per_direction_is_invoked() {
			this.manager = new VehicleRecordManagerImpl();
			this.manager.setDbFilePath(TEST_DB_FILE);
			try {
				this.actualCount = this.manager
						.retrieveTotalCountPerDirection(this.direction);
			} catch (final Exception e) {
				Assert.fail("Should not throw an exception");
			}
		}

		public void when_retrieve_total_count_per_direction_with_time_is_invoked() {
			this.manager = new VehicleRecordManagerImpl();
			this.manager.setDbFilePath(TEST_DB_FILE);
			try {
				this.actualCount = this.manager
						.retrieveTotalCountPerSessionDirection(this.session,
								this.direction, this.timeRange);
			} catch (final Exception e) {
				Assert.fail("Should not throw an exception: " + e.getMessage());
			}
		}

		public void when_retrieve_average_speed_per_direction_with_time_is_invoked() {
			this.manager = new VehicleRecordManagerImpl();
			this.manager.setDbFilePath(TEST_DB_FILE);
			try {
				this.actualAverageSpeed = this.manager
						.retrieveAverageSpeedPerSessionDirection(this.session,
								this.direction, this.timeRange);
			} catch (final Exception e) {
				Assert.fail("Should not throw an exception: " + e.getMessage());
			}
		}

		public void when_retrieve_session_end_time_is_invoked() {
			this.manager = new VehicleRecordManagerImpl();
			this.manager.setDbFilePath(TEST_DB_FILE);
			try {
				this.actualEndTime = this.manager
						.retrieveSessionEndTime(this.session);
			} catch (final Exception e) {
				Assert.fail("Should not throw an exception: " + e.getMessage());
			}
		}

		public void then_actual_count_and_expected_count_should_match() {
			Assert.assertTrue("actualCount: " + this.actualCount,
					this.actualCount == this.expectedCount);
		}

		public void then_actual_timestamp_and_expected_timestamp_should_match() {
			Assert.assertTrue("actualEndTime: " + this.actualEndTime,
					this.actualEndTime.equals(this.expectedEndTime));
		}

		public void then_actual_speed_and_expected_speed_should_match() {
			Assert.assertTrue("actualAverageSpeed: " + this.actualAverageSpeed,
					this.actualAverageSpeed == this.expectedAverageSpeed);
		}

	}

}
