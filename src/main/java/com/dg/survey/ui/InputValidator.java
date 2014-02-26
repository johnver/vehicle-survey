/**
 * 
 */
package com.dg.survey.ui;

import java.util.HashSet;
import java.util.Set;

/**
 * @author johnver
 * 
 */
public class InputValidator implements FormValidator {

	Set<Integer> validInputs = new HashSet<Integer>();

	public InputValidator(final Set<Integer> validInputs) {
		this.validInputs = validInputs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dg.survey.ui.FormValidator#isValid()
	 */
	public boolean isValid(final Form form) {

		boolean valid = false;
		final String input = form.getInput();

		if (input == null) {
			valid = false;
		}

		try {
			final Integer inputInt = new Integer(input);

			if (this.validInputs.contains(inputInt)) {
				valid = true;
			}
		} catch (final Exception e) {
			valid = false;
		}
		return valid;
	}
}
