package com.workday.util;

import java.util.Comparator;

public class EntryIdComparator implements Comparator<DataEntry> {

	@Override
	public int compare(DataEntry entry1, DataEntry entry2) {
		if(entry1.getId() == entry2.getId()) {
			return 0;
		}
		//in case (int) overflow
		return entry1.getId() - entry2.getId() < 0 ? -1 : 1;
	}

}
