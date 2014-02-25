/**
 * 
 */
package com.dg.survey.util;

import java.util.Comparator;

import com.dg.survey.ui.TotalCountRow;

/**
 * @author johnver
 * 
 */
public class SouthBoundComparator implements Comparator<TotalCountRow> {

	public int compare(final TotalCountRow o1, final TotalCountRow o2) {

		if (o1 == null) {
			if (o2 != null) {
				return -1;
			} else {
				return 0;
			}
		} else {
			return o2.getSouthBound() - o1.getSouthBound();
		}

	}

}
