package com.workday.util;
import java.util.*;

public class NumericIds implements Ids {
	
	PriorityQueue<DataEntry> pq;
	
	public NumericIds(PriorityQueue<DataEntry> pq) {
		this.pq = pq;
	}

	@Override
	public short nextId() {
		return pq != null && pq.size() > 0 ? pq.remove().getIndex() : Ids.END_OF_IDS;
	}
	
	@Override
	public long nextKey() {
		return pq != null && pq.size() > 0 ? pq.remove().getIndex() : Ids.END_OF_IDS;
	}

	@Override
	public DataEntry nextEntry() {
		return pq != null && pq.size() > 0 ? pq.remove() : null;
	}

}
