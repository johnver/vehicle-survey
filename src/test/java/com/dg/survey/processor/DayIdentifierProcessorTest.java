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
public class DayIdentifierProcessorTest {

	private DayIdentifierProcessorTestFixture testFixture;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testFixture = new DayIdentifierProcessorTestFixture();
	}

	@Test
	public void should_increment_context_day() {
		this.testFixture.given_a_roll_over_record();
		this.testFixture.when_process_method_is_invoked();
		this.testFixture.then_expected_and_actual_context_day_should_match();
		this.testFixture.then_vehicle_entity_day_must_be_set();
	}

	@Test
	public void should_be_the_same_day() {
		this.testFixture.given_a_valid_record();
		this.testFixture.when_process_method_is_invoked();
		this.testFixture.then_expected_and_actual_context_day_should_match();
		this.testFixture.then_vehicle_entity_day_must_be_set();
	}

	class DayIdentifierProcessorTestFixture {

		private DayIdentifierProcessor dayIdentifierProcessor;

		private VehicleRecord vehicleRecord;
		private VehicleRecordEntity vehicleRecordEntity;
		private LoadingContext context;

		private boolean result;
		private Integer expectedContextDay;

		public void given_a_roll_over_record() {
			final VehicleRecord previousPrevious = new VehicleRecord(null,
					"A86328899");
			final VehicleRecord previous = new VehicleRecord(previousPrevious,
					"B86328902");
			this.vehicleRecord = new VehicleRecord(previous, "A582668");
			this.vehicleRecordEntity = new VehicleRecordEntity(
					this.vehicleRecord);
			this.context = new LoadingContext();
			this.expectedContextDay = this.context.getDay() + 1;
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
			this.expectedContextDay = this.context.getDay();
		}

		public void when_process_method_is_invoked() {
			this.dayIdentifierProcessor = new DayIdentifierProcessor();
			try {
				this.dayIdentifierProcessor.process(this.vehicleRecord,
						this.vehicleRecordEntity, this.context);
				this.result = true;
			} catch (final Exception e) {
				this.result = false;
			}
		}

		public void then_the_result_should_be_true() {
			Assert.assertTrue(this.result == true);
		}

		public void then_expected_and_actual_context_day_should_match() {
			Assert.assertTrue(
					"this.context.getDay(): " + this.context.getDay(),
					this.context.getDay().equals(this.expectedContextDay));
		}

		public void then_vehicle_entity_day_must_be_set() {
			Assert.assertTrue(
					"this.vehicleRecordEntity.getDay(): "
							+ this.vehicleRecordEntity.getDay(),
					this.vehicleRecordEntity.getDay().equals(
							this.expectedContextDay.toString()));
		}
	}

}
