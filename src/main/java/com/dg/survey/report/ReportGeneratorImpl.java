/**
 * 
 */
package com.dg.survey.report;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.dg.survey.model.TimeRange;
import com.dg.survey.persistence.VehicleRecordManager;
import com.dg.survey.persistence.VehicleRecordManagerImpl;
import com.dg.survey.ui.Table;
import com.dg.survey.ui.TotalCountRow;
import com.dg.survey.util.AppConstants;
import com.dg.survey.util.NorthBoundComparator;
import com.dg.survey.util.SouthBoundComparator;
import com.dg.survey.util.TimeRangeUtil;

/**
 * @author johnver
 * 
 */
public class ReportGeneratorImpl implements
		ReportGenerator<TotalCountReportParameter> {

	private final VehicleRecordManager vehicleRecordManager;

	public ReportGeneratorImpl() {
		this.vehicleRecordManager = new VehicleRecordManagerImpl();
	}

	public Table generate(final TotalCountReportParameter parameter)
			throws Exception {

		final List<String> sessionList = new LinkedList<String>();

		if (parameter.getSessionType() > 0 && parameter.getSessionType() < 6) {
			final String session = new Integer(parameter.getSessionType() - 1)
					.toString();
			sessionList.add(session);
		} else if (parameter.getSessionType() == 6) {
			for (int i = 0; i < 5; i++) {
				final String session = new Integer(i).toString();
				sessionList.add(session);
			}

		}

		final List<TotalCountRow> totalCountRowList = new LinkedList<TotalCountRow>();
		try {

			for (final String session : sessionList) {
				final Timestamp sessionEndTime = this.vehicleRecordManager
						.retrieveSessionEndTime(session);

				final List<TimeRange> ranges = new LinkedList<TimeRange>();
				if (parameter.getPeriodType() == 1) {
					ranges.addAll(TimeRangeUtil.defineHourRanges(
							sessionEndTime, 12));
				} else if (parameter.getPeriodType() == 2) {
					ranges.addAll(TimeRangeUtil.defineHourRanges(
							sessionEndTime, 1));
				} else if (parameter.getPeriodType() == 3) {
					ranges.addAll(TimeRangeUtil.defineMinuteRanges(
							sessionEndTime, 30));
				} else if (parameter.getPeriodType() == 4) {
					ranges.addAll(TimeRangeUtil.defineMinuteRanges(
							sessionEndTime, 20));
				} else if (parameter.getPeriodType() == 5) {
					ranges.addAll(TimeRangeUtil.defineMinuteRanges(
							sessionEndTime, 15));
				}

				if (ranges.isEmpty()) {
					if (parameter.getReportType() == 1
							|| parameter.getReportType() == 2) {
						this.calculateCount(parameter, totalCountRowList,
								session);
					} else if (parameter.getReportType() == 3) {

						this.calculateSpeed(parameter, totalCountRowList,
								session);
					} else if (parameter.getReportType() == 4) {

						this.calculateDistance(parameter, totalCountRowList,
								session);
					}

				} else {

					for (final TimeRange timeRange : ranges) {

						if (parameter.getReportType() == 1
								|| parameter.getReportType() == 2) {
							this.calculateCount(totalCountRowList, session,
									timeRange);
						} else if (parameter.getReportType() == 3) {
							this.calculateSpeed(totalCountRowList, session,
									timeRange);
						} else if (parameter.getReportType() == 4) {
							this.calculateDistance(totalCountRowList, session,
									timeRange);
						}

					}
				}

			}

			if (parameter.getDirectionType() == 1) {
				Collections.sort(totalCountRowList, new SouthBoundComparator());
			} else if (parameter.getDirectionType() == 2) {
				Collections.sort(totalCountRowList, new NorthBoundComparator());
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		final List<String> header = new LinkedList<String>();
		header.add("Day");
		header.add("Time");
		if (parameter.getReportType() == 1 || parameter.getReportType() == 2) {
			header.add("Southbound");
			header.add("Northbound");
		} else if (parameter.getReportType() == 3) {
			header.add("Southbound km/h");
			header.add("Northbound km/h");
		} else if (parameter.getReportType() == 4) {
			header.add("Southbound Ave Distance (km)");
			header.add("Northbound Ave Distance (km)");
		}

		final Table table = new Table(header);
		for (final TotalCountRow totalCountRow : totalCountRowList) {
			table.addRow(totalCountRow.getSession(), totalCountRow
					.getDisplayName(),
					totalCountRow.getSouthBound().toString(), totalCountRow
							.getNorthBound().toString());
		}

		return table;
	}

	private void calculateDistance(final TotalCountReportParameter parameter,
			final List<TotalCountRow> totalCountRowList, final String session)
			throws Exception {
		final int southBoundCount = (int) this.vehicleRecordManager
				.retrieveAverageDistancePerSessionDirection(session,
						AppConstants.SOUTHBOUND);

		final int northBoundCount = (int) this.vehicleRecordManager
				.retrieveAverageDistancePerSessionDirection(session,
						AppConstants.NORTHBOUND);
		final TotalCountRow row = new TotalCountRow("Session "
				+ parameter.getSessionType(), " ",
				new Integer(southBoundCount), new Integer(northBoundCount));
		totalCountRowList.add(row);
	}

	private void calculateSpeed(final TotalCountReportParameter parameter,
			final List<TotalCountRow> totalCountRowList, final String session)
			throws Exception {
		final int southBoundCount = (int) this.vehicleRecordManager
				.retrieveAverageSpeedPerSessionDirection(session,
						AppConstants.SOUTHBOUND);

		final int northBoundCount = (int) this.vehicleRecordManager
				.retrieveAverageSpeedPerSessionDirection(session,
						AppConstants.NORTHBOUND);
		final TotalCountRow row = new TotalCountRow("Session "
				+ parameter.getSessionType(), " ",
				new Integer(southBoundCount), new Integer(northBoundCount));
		totalCountRowList.add(row);
	}

	private void calculateCount(final TotalCountReportParameter parameter,
			final List<TotalCountRow> totalCountRowList, final String session)
			throws Exception {
		final int southBoundCount = this.vehicleRecordManager
				.retrieveTotalCountPerSessionDirection(session,
						AppConstants.SOUTHBOUND);

		final int northBoundCount = this.vehicleRecordManager
				.retrieveTotalCountPerSessionDirection(session,
						AppConstants.NORTHBOUND);
		final TotalCountRow row = new TotalCountRow("Session "
				+ parameter.getSessionType(), " ",
				new Integer(southBoundCount), new Integer(northBoundCount));
		totalCountRowList.add(row);
	}

	private void calculateSpeed(final List<TotalCountRow> totalCountRowList,
			final String session, final TimeRange timeRange) throws Exception {
		final int southBoundSpeed = (int) this.vehicleRecordManager
				.retrieveAverageSpeedPerSessionDirection(session,
						AppConstants.SOUTHBOUND, timeRange);

		final int northBoundSpeed = (int) this.vehicleRecordManager
				.retrieveAverageSpeedPerSessionDirection(session,
						AppConstants.NORTHBOUND, timeRange);

		final String displayDay = "" + (new Integer(session) + 1);
		final TotalCountRow row = new TotalCountRow(displayDay,
				timeRange.getDisplay(), new Integer(southBoundSpeed),
				new Integer(northBoundSpeed));
		totalCountRowList.add(row);
	}

	private void calculateDistance(final List<TotalCountRow> totalCountRowList,
			final String session, final TimeRange timeRange) throws Exception {
		final int southBoundDistance = (int) this.vehicleRecordManager
				.retrieveAverageDistancePerSessionDirection(session,
						AppConstants.SOUTHBOUND, timeRange);

		final int northBoundDistance = (int) this.vehicleRecordManager
				.retrieveAverageDistancePerSessionDirection(session,
						AppConstants.NORTHBOUND, timeRange);

		final String displayDay = "" + (new Integer(session) + 1);
		final TotalCountRow row = new TotalCountRow(displayDay,
				timeRange.getDisplay(), new Integer(southBoundDistance),
				new Integer(northBoundDistance));
		totalCountRowList.add(row);
	}

	private void calculateCount(final List<TotalCountRow> totalCountRowList,
			final String session, final TimeRange timeRange) throws Exception {
		final int southBoundCount = this.vehicleRecordManager
				.retrieveTotalCountPerSessionDirection(session,
						AppConstants.SOUTHBOUND, timeRange);

		final int northBoundCount = this.vehicleRecordManager
				.retrieveTotalCountPerSessionDirection(session,
						AppConstants.NORTHBOUND, timeRange);

		final String displayDay = "" + (new Integer(session) + 1);
		final TotalCountRow row = new TotalCountRow(displayDay,
				timeRange.getDisplay(), new Integer(southBoundCount),
				new Integer(northBoundCount));
		totalCountRowList.add(row);
	}
}
