/**
 * 
 */
package com.dg.survey.manager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.dg.survey.model.TimeRange;
import com.dg.survey.persistence.VehicleRecordManagerImpl;
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
	public void should_retrieve_total_count_per_with_timestamp() {
		this.testFixture.given_a_timerange();
		this.testFixture
				.when_retrieve_total_count_per_direction_with_time_is_invoked();
		this.testFixture.then_actual_count_and_expected_count_should_match();
	}

	class VehicleRecordManagerImplTestFixture {

		private VehicleRecordManagerImpl manager;

		private int expectedCount;
		private int actualCount;
		private String direction;
		String session;
		private TimeRange timeRange;

		private static final String TEST_DB_FILE = "app-data/test-db.txt";

		public void given_a_direction() {
			this.direction = "S";
			this.expectedCount = 10;
			this.session = "0";
		}

		public void given_a_timerange() {
			this.direction = "S";
			this.expectedCount = 10;
			this.session = "0";
			this.timeRange = TimeRangeUtil.HALF_DAY.get(1);
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
				Assert.fail("Should not throw an exception");
			}
		}

		public void then_actual_count_and_expected_count_should_match() {
			Assert.assertTrue("actualCount: " + this.actualCount,
					this.actualCount == this.expectedCount);
		}

	}

}
