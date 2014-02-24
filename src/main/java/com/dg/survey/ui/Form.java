/**
 * 
 */
package com.dg.survey.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author johnver
 * 
 */
public class Form implements UIDisplay {

	private final String message;
	private final List<String> options;
	private String input;

	public Form(final String message) {
		this(message, null);
	}

	public Form(final String message, final List<String> options) {
		this.message = message;
		this.options = options;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dg.survey.ui.UIDisplay#display()
	 */
	public void display() {

		if (this.options != null) {
			for (final String option : this.options) {
				System.out.println(option);
			}
		}

		try {
			System.out.print(this.message + ": ");
			final BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			String input;
			while ((input = br.readLine()) != null) {
				this.input = input;
				break;
			}

		} catch (final IOException io) {
			io.printStackTrace();
		}
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return this.input;
	}

}
