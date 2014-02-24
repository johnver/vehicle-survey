/**
 * 
 */
package com.dg.survey.processor;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.dg.survey.analyzer.VehicleRecord;
import com.dg.survey.model.LoadingContext;
import com.dg.survey.model.VehicleRecordEntity;

/**
 * @author johnver
 * 
 */
public class SecondAxleIdentifierProcessorTest {

	private SecondAxleIdentifierProcessorTestFixture testFixture;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testFixture = new SecondAxleIdentifierProcessorTestFixture();
	}

	@Test
	public void should_be_a_new_vehicle() {
		this.testFixture.given_a_roll_over_record();
		this.testFixture.when_process_method_is_invoked();
		this.testFixture.then_vehicle_entity_axle_must_be_set();

	}

	@Test
	public void should_be_the_same_vehicle() {
		this.testFixture.given_a_valid_record();
		this.testFixture.when_process_method_is_invoked();
		this.testFixture.then_vehicle_entity_axle_must_be_set();
	}

	class SecondAxleIdentifierProcessorTestFixture {

		private SecondAxleIdentifierProcessor secondAxleIdentifierProcessor;

		private VehicleRecord vehicleRecord;
		private VehicleRecordEntity vehicleRecordEntity;
		private LoadingContext context;

		private boolean result;
		private String axle;

		public void given_a_roll_over_record() {
			final VehicleRecord previousPrevious = new VehicleRecord(null,
					"A86328899");
			final VehicleRecord previous = new VehicleRecord(previousPrevious,
					"B86328902");
			this.vehicleRecord = new VehicleRecord(previous, "A582668");
			this.vehicleRecordEntity = new VehicleRecordEntity(
					this.vehicleRecord);
			this.context = new LoadingContext();
			this.axle = "1";
		}

		public void given_a_valid_record() {
			final VehicleRecord previousPrevious = new VehicleRecord(null,
					"A86328771");
			final VehicleRecord previous = new VehicleRecord(previousPrevious,
					"B86328774");
			this.vehicleRecord = new VehicleRecord(previous, "A86328899");
			this.vehicleRecordEntity = new VehicleRecordEntity(
					this.vehicleRecord);
			this.context = new LoadingContext();
			this.axle = "2";
		}

		public void when_process_method_is_invoked() {
			this.secondAxleIdentifierProcessor = new SecondAxleIdentifierProcessor();
			try {
				this.secondAxleIdentifierProcessor.process(this.vehicleRecord,
						this.vehicleRecordEntity, this.context);
				this.result = true;
			} catch (final Exception e) {
				this.result = false;
			}
		}

		public void then_the_result_should_be_true() {
			Assert.assertTrue(this.result == true);
		}

		public void then_vehicle_entity_axle_must_be_set() {
			Assert.assertTrue("this.vehicleRecordEntity.getAxle(): "
					+ this.vehicleRecordEntity.getAxle(),
					this.vehicleRecordEntity.getAxle().equals(this.axle));
		}
	}

}
