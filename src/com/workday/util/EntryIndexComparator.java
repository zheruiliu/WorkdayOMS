package com.workday.util;

import java.util.Comparator;

public class EntryIndexComparator implements Comparator<DataEntry> {

	@Override
	public int compare(DataEntry entry1, DataEntry entry2) {
		return entry1.getIndex() - entry2.getIndex();
	}

}
