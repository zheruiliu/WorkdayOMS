package com.workday.util;

import java.util.PriorityQueue;

public class JoinIds implements Ids {

	private PriorityQueue<DataEntry> pq;
	private int totalContainer;
	
	public JoinIds(int totalContainer) {
		this.totalContainer = totalContainer;
		//first value only is default value in under java8, noting to do with pq.size()
		this.pq = new PriorityQueue<DataEntry>(10, new EntryIdComparator());
	}
	
	public synchronized void add(DataEntry dataEntry) {
		pq.add(dataEntry);
	}

	@Override
	public short nextId() {
		return pq.size() > 0 ? (short)pq.remove().getId() : Ids.END_OF_IDS;
	}
	
	@Override
	public long nextKey() {
		return pq.size() > 0 ? pq.remove().getId() : Ids.END_OF_IDS;
	}

	@Override
	public DataEntry nextEntry() {
		return pq.size() > 0 ? pq.remove() : null;
	}
	
	public synchronized void waitFinish() {
		while(totalContainer != 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void finishOne() {
		totalContainer--;
		this.notify();
	}
}
