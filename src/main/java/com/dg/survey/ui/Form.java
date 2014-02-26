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
	private final FormValidator formValidator;

	public Form(final String message) {
		this(message, null);
	}

	public Form(final String message, final List<String> options) {
		this(message, options, null);
	}

	public Form(final String message, final List<String> options,
			final FormValidator formValidator) {
		this.message = message;
		this.options = options;
		this.formValidator = formValidator;
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
			
			if (formValidator != null) {
				
				while (!formValidator.isValid(this)) {
					askForInput();
				}
			}
			else {
				askForInput();
			}
			

		} catch (final IOException io) {
			io.printStackTrace();
		}
	}

	private void askForInput() throws IOException {
		System.out.print(this.message + ": ");
		final BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in));
		String input;
		while ((input = br.readLine()) != null) {
			this.input = input;
			break;
		}
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return this.input;
	}

}
