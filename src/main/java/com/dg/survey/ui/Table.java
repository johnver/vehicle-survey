/**
 * 
 */
package com.dg.survey.ui;

import java.util.LinkedList;
import java.util.List;

/**
 * @author johnver
 * 
 */
public class Table implements UIDisplay {

	private List<String> headers;
	private final List<List<String>> data;

	public Table(final List<String> header) {
		this.headers = header;
		this.data = new LinkedList<List<String>>();
	}

	public Table(final List<String> header, final List<List<String>> data) {
		this.headers = header;
		this.data = data;
	}

	/**
	 * @return the header
	 */
	public List<String> getHeaders() {
		return this.headers;
	}

	/**
	 * @return the data
	 */
	public List<List<String>> getData() {
		return this.data;
	}

	public void setHeaders(final List<String> headers) {
		this.headers = headers;
	}

	public int columnCount() {
		if (this.headers.isEmpty() && !this.data.isEmpty()) {
			return this.data.get(0).size();
		}
		return this.headers.size();
	}

	public String getHeaderAt(final int idx) {
		return this.headers.get(idx);
	}

	public void setHeaderAt(final int idx, final String value) {
		this.headers.set(idx, value);
	}

	public void addHeader(final String value) {
		this.addHeader(this.headers.size(), value);
	}

	public void addHeader(final int idx, final String value) {
		this.headers.add(idx, value);
	}

	public int rowCount() {
		return this.data.size();
	}

	public List<String> getRowAt(final int row) {
		return this.data.get(row);
	}

	public void addRow(final String... colValues) {
		this.addRow(this.data.size(), colValues);
	}

	public void addRow(final Object[] colValues) {
		final List<String> temp = new LinkedList<String>();
		for (final Object colValue : colValues) {
			if (colValue == null) {
				temp.add("");
			} else {
				temp.add(colValue.toString());
			}
		}
		this.addRow(temp);
	}

	public void addRow(final int idx, final String... colValues) {
		final List<String> temp = new LinkedList<String>();
		for (final String colValue : colValues) {
			temp.add(colValue);
		}
		this.addRow(idx, temp);
	}

	public void addRow(final List<String> row) {
		this.addRow(this.data.size(), row);
	}

	public void addRow(final int idx, final List<String> row) {
		this.data.add(idx, row);
	}

	public void setRowAt(final int idx, final List<String> row) {
		this.data.set(idx, row);
	}

	public void setDataAt(final int row, final int col, final String value) {
		this.getRowAt(row).set(col, value);
	}

	public String getDataAt(final int row, final int col) {
		return this.getRowAt(row).get(col);
	}

	public String[][] as2DStringArray() {
		final String[][] retVal = new String[this.rowCount()][this
				.columnCount()];

		for (int i = 0; i < this.rowCount(); ++i) {
			for (int j = 0; j < this.columnCount(); ++j) {
				if (this.getDataAt(i, j) != null
						&& !this.getDataAt(i, j).equals("")) {
					retVal[i][j] = this.getDataAt(i, j);
				} else {
					retVal[i][j] = "";
				}
			}
		}
		return retVal;
	}

	public void display() {

		for (int i = 0; i < this.headers.size(); i++) {
			System.out.printf("%25s", this.headers.get(i));
			System.out.print("\t");
		}
		System.out.println();
		for (int i = 0; i < this.rowCount(); ++i) {
			for (int j = 0; j < this.columnCount(); ++j) {
				if (this.getDataAt(i, j) != null
						&& !this.getDataAt(i, j).equals("")) {
					System.out.printf("%25s", this.getDataAt(i, j));
					System.out.print("\t");
				}
			}
			System.out.println();
		}

	}
}
