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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dg.survey.model.VehicleRecordEntity;

/**
 * @author johnver
 * 
 */
public class VehicleRecordManagerImpl implements VehicleRecordManager {

	private static String TEMP_DB_FILE = "db.txt";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dg.survey.persistence.VehicleRecordManager#create(com.dg.survey.model
	 * .VehicleRecordEntity)
	 */
	public void create(final VehicleRecordEntity vehicleItem) throws Exception {
		final String data = vehicleItem.getPersistentString();

		final File file = new File(TEMP_DB_FILE);

		// true = append file
		final FileWriter fileWritter = new FileWriter(file.getName(), true);
		final BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(data);
		bufferWritter.newLine();
		bufferWritter.close();
	}

	public void deleteDatabase() throws Exception {
		final File file = new File(TEMP_DB_FILE);

		if (file.delete()) {
			System.out.println(file.getName() + " cleanup.");
		} else {
			System.out.println("Delete operation is failed.");
		}
	}

	public void createDatabase() throws Exception {
		final File file = new File(TEMP_DB_FILE);

		if (!file.exists()) {
			System.out.println(file.getName() + " is created.");
			file.createNewFile();
		}
	}

	public int retrieveTotalCountPerDirection(final String direction)
			throws Exception {
		final int count = 0;
		BufferedReader br = null;

		final Pattern regexp = Pattern.compile("^[A,B]\\d|");
		final Matcher matcher = regexp.matcher("");

		try {

			String sCurrentLine;
			final InputStreamReader inputStreamReader = new FileReader(
					TEMP_DB_FILE);
			br = new BufferedReader(inputStreamReader);

			while ((sCurrentLine = br.readLine()) != null) {
				matcher.reset(sCurrentLine); // reset the input
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

	public int retrieveTotalCountPerSessionDirection(final String session,
			final String direction) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
