/**
 * 
 */
package com.dg.survey.report;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.dg.survey.model.TimeRange;
import com.dg.survey.persistence.VehicleRecordManager;
import com.dg.survey.persistence.VehicleRecordManagerImpl;
import com.dg.survey.ui.Table;
import com.dg.survey.util.AppConstants;
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

	public Table generate(final TotalCountReportParameter parameter) {

		final List<String> header = new LinkedList<String>();
		header.add("Time");
		header.add("Southbound");
		header.add("Northbound");

		String session = "";

		if (parameter.getSessionType() > 0 && parameter.getSessionType() < 6) {
			session = new Integer(parameter.getSessionType() - 1).toString();
		}

		final List<TimeRange> ranges = new ArrayList<TimeRange>();
		if (parameter.getPeriodType() == 1) {
			ranges.addAll(TimeRangeUtil.HALF_DAY);
		} else if (parameter.getPeriodType() == 2) {
			ranges.addAll(TimeRangeUtil.PER_HOUR);
		} else if (parameter.getPeriodType() == 3) {
			ranges.addAll(TimeRangeUtil.PER_HALF_HOUR);
		} else if (parameter.getPeriodType() == 4) {
			ranges.addAll(TimeRangeUtil.PER_20_MINUTES);
		} else if (parameter.getPeriodType() == 5) {
			ranges.addAll(TimeRangeUtil.PER_15_MINUTES);
		}

		final Table table = new Table(header);
		try {

			if (ranges.isEmpty()) {
				final int southBoundCount = this.vehicleRecordManager
						.retrieveTotalCountPerSessionDirection(session,
								AppConstants.SOUTHBOUND);

				final int northBoundCount = this.vehicleRecordManager
						.retrieveTotalCountPerSessionDirection(session,
								AppConstants.NORTHBOUND);

				table.addRow("Session " + parameter.getSessionType(),
						new Integer(southBoundCount).toString(), new Integer(
								northBoundCount).toString());
			} else {
				for (final TimeRange timeRange : ranges) {
					final int southBoundCount = this.vehicleRecordManager
							.retrieveTotalCountPerSessionDirection(session,
									AppConstants.SOUTHBOUND, timeRange);

					final int northBoundCount = this.vehicleRecordManager
							.retrieveTotalCountPerSessionDirection(session,
									AppConstants.NORTHBOUND, timeRange);

					table.addRow(timeRange.getDisplay(), new Integer(
							southBoundCount).toString(), new Integer(
							northBoundCount).toString());

				}
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return table;
	}
}
