/**
 * 
 */
package com.dg.survey.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dg.survey.model.TimeRange;
import com.dg.survey.model.VehicleRecordEntity;
import com.dg.survey.util.AppConstants;

/**
 * @author johnver
 * 
 */
public class VehicleRecordManagerImpl implements VehicleRecordManager {

	private static String TEMP_DB_FILE = "app-data/db.txt";

	private String dbFilePath = TEMP_DB_FILE;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dg.survey.persistence.VehicleRecordManager#create(com.dg.survey.model
	 * .VehicleRecordEntity)
	 */
	public void create(final VehicleRecordEntity vehicleItem) throws Exception {
		final String data = vehicleItem.getPersistentString();

		final File file = new File(this.dbFilePath);

		// true = append file
		final FileWriter fileWritter = new FileWriter(file, true);
		final BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(data);
		bufferWritter.newLine();
		bufferWritter.close();
	}

	public void deleteDatabase() throws Exception {
		final File file = new File(this.dbFilePath);

		if (file.delete()) {
			System.out.println(file.getName() + " cleanup.");
		} else {
			System.out.println("Delete operation failed.");
		}
	}

	public void createDatabase() throws Exception {
		final File file = new File(this.dbFilePath);

		if (!file.exists()) {
			System.out.println(file.getName() + " is created.");
			file.createNewFile();
		}
	}

	public int retrieveTotalCountPerDirection(final String direction)
			throws Exception {

		return this.retrieveTotalCountPerSessionDirection("", direction);
	}

	public int retrieveTotalCountPerSessionDirection(final String session,
			final String direction) throws Exception {
		return this.retrieveTotalCountPerSessionDirection(session, direction,
				null);
	}

	public int retrieveTotalCountPerSessionDirection(final String session,
			final String direction, final TimeRange timerange) throws Exception {
		int count = 0;
		BufferedReader br = null;

		String rawDataRegEx = "^[A,B]\\d*";
		String sessionRegEx = "\\d";
		final String axleRegEx = "\\d";
		String timeStampRegEx = ".*";
		String directionRegEx = "\\w";
		final String speedRegEx = ".*";

		if (session != null && !"".equalsIgnoreCase(session)) {
			sessionRegEx = session;
		}

		if (direction != null && !"".equalsIgnoreCase(direction)) {
			if (AppConstants.SOUTHBOUND.equalsIgnoreCase(direction)) {
				rawDataRegEx = "^B\\d*";
			}
			directionRegEx = direction;
		}

		if (timerange != null) {
			timeStampRegEx = timerange.getRegEx();
		}

		final StringBuilder regExBuilder = new StringBuilder();
		regExBuilder.append(rawDataRegEx);
		regExBuilder.append(AppConstants.ESCAPE_CHAR + AppConstants.DELIMITER);
		regExBuilder.append(sessionRegEx);
		regExBuilder.append(AppConstants.ESCAPE_CHAR + AppConstants.DELIMITER);
		regExBuilder.append(axleRegEx);
		regExBuilder.append(AppConstants.ESCAPE_CHAR + AppConstants.DELIMITER);
		regExBuilder.append(timeStampRegEx);
		regExBuilder.append(AppConstants.ESCAPE_CHAR + AppConstants.DELIMITER);
		regExBuilder.append(directionRegEx);
		regExBuilder.append(AppConstants.ESCAPE_CHAR + AppConstants.DELIMITER);
		regExBuilder.append(speedRegEx);

		final Pattern regexp = Pattern.compile(regExBuilder.toString());
		final Matcher matcher = regexp.matcher("");

		try {

			String sCurrentLine;
			final InputStreamReader inputStreamReader = new FileReader(
					this.dbFilePath);
			br = new BufferedReader(inputStreamReader);

			final DateFormat format = new SimpleDateFormat(
					AppConstants.TIMESTAMP_FORMAT);

			while ((sCurrentLine = br.readLine()) != null) {
				matcher.reset(sCurrentLine); // reset the input
				if (matcher.matches()) {
					if (timerange != null) {
						// System.out.println(sCurrentLine);
						final String timestamp = matcher.group(1);
						final java.util.Date date = format.parse(timestamp);
						final Timestamp aTime = new Timestamp(date.getTime());
						if (timerange.isInRange(aTime)) {
							count++;
						}
					} else {
						count++;
					}

				}

			}

		} catch (final Exception e) {
			throw e;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * @return the dbFilePath
	 */
	public String getDbFilePath() {
		return this.dbFilePath;
	}

	/**
	 * @param dbFilePath
	 *            the dbFilePath to set
	 */
	public void setDbFilePath(final String dbFilePath) {
		this.dbFilePath = dbFilePath;
	}

}
