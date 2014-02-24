/**
 * 
 */
package com.dg.survery.model;

import java.text.DateFormat;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.dg.survey.analyzer.VehicleRecord;

/**
 * @author johnver
 * 
 */
public class VehicleRecordTest {

	private VehicleRecordTestFixture testFixture;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testFixture = new VehicleRecordTestFixture();

	}

	@Test
	public void should_not_instantiate_an_object() {
		this.testFixture.given_an_invalid_record();
		this.testFixture.when_object_is_instantiated();
		this.testFixture.then_result_should_be_false();
	}

	@Test
	public void should_instantiate_an_object() {
		this.testFixture.given_a_valid_record();
		this.testFixture.when_object_is_instantiated();
		this.testFixture.then_result_should_be_true();
	}

	@Test
	public void should_parse_record() {
		this.testFixture.given_a_valid_record();
		this.testFixture.when_object_is_instantiated();
		this.testFixture.then_check_if_fields_are_set();
	}

	@Test
	public void should_match_the_timestamp() {
		this.testFixture.given_a_valid_record();
		this.testFixture.when_object_is_instantiated();
		this.testFixture.then_check_if_timestamp_is_correct();
	}

	class VehicleRecordTestFixture {

		private String record;
		private VehicleRecord previous;

		VehicleRecord vehicleRecord;
		private boolean result;

		private String expectedTimestampStr;

		public void given_an_invalid_record() {
			this.record = null;
			this.previous = null;
		}

		public void given_a_valid_record() {
			this.record = "A268981";
			this.previous = null;
			this.expectedTimestampStr = "12:04:28.981";
		}

		public void when_object_is_instantiated() {
			try {
				this.vehicleRecord = new VehicleRecord(this.previous,
						this.record);
				this.result = true;
			} catch (final Exception e) {
				this.result = false;
			}
		}

		public void then_result_should_be_true() {
			Assert.assertTrue(this.result == true);
		}

		public void then_result_should_be_false() {
			Assert.assertTrue(this.result == false);
		}

		public void then_check_if_fields_are_set() {
			Assert.assertTrue(this.vehicleRecord.getPrefix() != null);
			Assert.assertTrue(this.vehicleRecord.getTimestamp() != null);
			Assert.assertTrue(this.vehicleRecord.getPrevious() == this.previous);
			Assert.assertTrue(this.vehicleRecord.getRecord()
					.equals(this.record));
		}

		public void then_check_if_timestamp_is_correct() {
			final DateFormat df = VehicleRecord.DATE_FORMAT;
			final String actualOutput = df.format(this.vehicleRecord
					.getTimestamp());
			Assert.assertTrue(this.expectedTimestampStr.equals(actualOutput));

		}

	}
}
