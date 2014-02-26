/**
 * 
 */
package com.dg.survey;

import java.io.InputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.dg.survey.analyzer.VehicleSurveyAnalyzer;
import com.dg.survey.analyzer.VehicleSurveyAnalyzerImpl;
import com.dg.survey.report.ReportGenerator;
import com.dg.survey.report.ReportGeneratorImpl;
import com.dg.survey.report.TotalCountReportParameter;
import com.dg.survey.ui.Form;
import com.dg.survey.ui.FormValidator;
import com.dg.survey.ui.InputValidator;
import com.dg.survey.ui.Table;

/**
 * @author johnver
 * 
 */
public class VehicleSurveyMain {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		Boolean analyze = true;
		if (args.length > 0) {
			analyze = new Boolean(args[0]);
		}
		if (analyze) {
			final VehicleSurveyAnalyzer analyzer = new VehicleSurveyAnalyzerImpl();
			final InputStream inputStream = analyzer.getClass()
					.getClassLoader().getResourceAsStream("sample.txt");
			try {
				analyzer.analyze(inputStream);
			} catch (final Exception e) {
				System.err.println(e.getMessage());
			}
		}

		String nextLine = "doStuff";
		do {
			System.out.println("==============");

			final String message = "Please select an option for the report output";
			final List<String> options = new LinkedList<String>();
			options.add("[1] - Total vehicle count in each direction.");
			options.add("[2] - Peak volume times.");
			options.add("[3] - Speed distribution of traffic.");
			options.add("[4] - Rough distance between cars.");
			options.add("[5] - Exit");
			final Set<Integer> validOptions = new HashSet<Integer>();
			for (int i = 1; i <= 5; i++) {
				validOptions.add(i);
			}
			final FormValidator validator = new InputValidator(validOptions);
			final Form reportForm = new Form(message, options, validator);
			reportForm.display();
			final int reportType = new Integer(reportForm.getInput());

			if (reportType == 5) {
				// User chooses to Exit;
				nextLine = null;
				System.out.println("Thank you.");
				break;
			}

			System.out.println("==============");

			final String sessionFormMessage = "Please select an option for the session you want to display";
			final List<String> sessionOptions = new LinkedList<String>();
			sessionOptions.add("[1] - Session 1");
			sessionOptions.add("[2] - Session 2");
			sessionOptions.add("[3] - Session 3");
			sessionOptions.add("[4] - Session 4");
			sessionOptions.add("[5] - Session 5");
			sessionOptions.add("[6] - All");

			final Set<Integer> sessionFormValidOptions = new HashSet<Integer>();
			for (int i = 1; i <= 6; i++) {
				sessionFormValidOptions.add(i);
			}
			final FormValidator sessionValidator = new InputValidator(
					sessionFormValidOptions);
			final Form sessionForm = new Form(sessionFormMessage,
					sessionOptions, sessionValidator);
			sessionForm.display();
			final int sessionType = new Integer(sessionForm.getInput());

			System.out.println("==============");

			final String periodFormMessage = "Please select an option for the period you want to display";
			final List<String> periodOptions = new LinkedList<String>();
			periodOptions.add("[1] - Morning/Evening");
			periodOptions.add("[2] - Per Hour");
			periodOptions.add("[3] - Per Half Hour");
			periodOptions.add("[4] - Per 20 minutes");
			periodOptions.add("[5] - Per 15 minutes");
			periodOptions.add("[6] - All");

			final Set<Integer> periodFormValidOptions = new HashSet<Integer>();
			for (int i = 1; i <= 6; i++) {
				periodFormValidOptions.add(i);
			}
			final FormValidator periodValidator = new InputValidator(
					periodFormValidOptions);

			final Form periodForm = new Form(periodFormMessage, periodOptions,
					periodValidator);
			periodForm.display();
			final int periodType = new Integer(periodForm.getInput());

			int directionType = -1;
			if (reportType == 2) {
				System.out.println("==============");

				final String directionMessage = "Please select the direction to sort the peak hours.";
				final List<String> directionOptions = new LinkedList<String>();
				directionOptions.add("[1] - Southbound");
				directionOptions.add("[2] - Northbound");
				final Set<Integer> directionValidOptions = new HashSet<Integer>();
				for (int i = 1; i <= 2; i++) {
					directionValidOptions.add(i);
				}
				final FormValidator directionValidator = new InputValidator(
						directionValidOptions);

				final Form directionForm = new Form(directionMessage,
						directionOptions, directionValidator);
				directionForm.display();
				directionType = new Integer(directionForm.getInput());

			}

			System.out.println("==============");
			System.out.println();
			System.out.println("Generating the report... Please wait.");
			System.out.println();

			if (reportType == 1 || reportType == 2 || reportType == 3
					|| reportType == 4) {
				final ReportGenerator<TotalCountReportParameter> totalCountReport = new ReportGeneratorImpl();
				final TotalCountReportParameter parameter = new TotalCountReportParameter();
				parameter.setReportType(reportType);
				parameter.setSessionType(sessionType);
				parameter.setPeriodType(periodType);
				parameter.setDirectionType(directionType);
				Table table;
				try {
					table = totalCountReport.generate(parameter);
					table.display();
				} catch (final Exception e) {
					System.out
							.println("There was a problem in generating the report.");
				}

				System.out.println();
				System.out.println("Press enter to continue...");
				final Scanner keyboard = new Scanner(System.in);
				nextLine = keyboard.nextLine();
			}
		} while (nextLine != null);
	}
}
