/**
 * 
 */
package com.dg.survey.ui;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author johnver
 * 
 */
public class TableTest {

	private TableTestFixture testFixture;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.testFixture = new TableTestFixture();
	}

	@Test
	public void should_display_output() {
		this.testFixture.given_headers_and_data();
		this.testFixture.when_display_is_invoke();
	}

	class TableTestFixture {
		Table table;
		List<String> headers;
		List<List<String>> data;

		public void given_headers_and_data() {
			final List<String> headerSample = new LinkedList<String>();
			headerSample.add("Time");
			headerSample.add("Southbound");
			headerSample.add("Northbound");
			this.headers = headerSample;

			final List<List<String>> sampleData = new LinkedList<List<String>>();
			final List<String> sampleRow = new LinkedList<String>();
			sampleRow.add("Session 1");
			sampleRow.add("2222");
			sampleRow.add("3333");
			sampleData.add(sampleRow);

			this.data = sampleData;

		}

		public void when_display_is_invoke() {
			this.table = new Table(this.headers);
			for (final List<String> row : this.data) {
				this.table.addRow(row);
			}

			this.table.display();

		}
	}

}
