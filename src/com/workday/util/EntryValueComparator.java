package com.workday.util;

import java.util.Comparator;

public class EntryValueComparator implements Comparator<DataEntry> {

	public int compare(DataEntry entry1, DataEntry entry2) {
		if(entry1 == null || entry2 == null) {
			return entry1 == null ? 1 : -1;
		}
		if(entry1.getValue() - entry2.getValue() == 0) {
			return 0;
		}
		return entry1.getValue() - entry2.getValue() > 0 ? 1 : -1;
	}

}
