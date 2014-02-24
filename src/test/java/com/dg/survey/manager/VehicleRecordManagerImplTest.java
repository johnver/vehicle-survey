/**
 * 
 */
package com.dg.survey.manager;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.dg.survey.persistence.VehicleRecordManagerImpl;

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

	}

	/**
	 * Test method for
	 * {@link com.dg.survey.persistence.VehicleRecordManagerImpl#retrieveTotalCountPerSessionDirection(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void should_retrieve_total_count_per_session_per_direction() {
		fail("Not yet implemented");
	}

	class VehicleRecordManagerImplTestFixture {

		private VehicleRecordManagerImpl manager;

		private int expectedCount;

		public void given_a__direction() {

		}

		public void when_retrieve_total_count_per_direction_is_invoked() {

		}

		public void then_actual_count_and_expected_count_should_match() {

		}

	}

}
