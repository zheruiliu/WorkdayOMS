package com.workday.util;

import java.util.Arrays;
import java.util.*;

public class NumericRangeContainer implements RangeContainer {
	
	private DataEntry[] entries;
	private int id = 0;
	private int size;
	
	/**
	 * default rangeContainer size
	 */
	private int maxSize = 32000 / 8;

	public NumericRangeContainer() {
		size = 0;
		entries = new DataEntry[maxSize];
	}
	
	public NumericRangeContainer(long[] data) {
		this();
		loadEntries(data);
	}
	
	public NumericRangeContainer(int maxSize) {
		this.maxSize = maxSize;
		this.size = 0;
		entries = new DataEntry[maxSize];
	}
	
	public NumericRangeContainer(int maxSize, long[] data) {
		this(maxSize);
		loadEntries(data);
	}
	
	public NumericRangeContainer(int maxSize, int id) {
		this(maxSize);
		this.id = id;
	}
	

	public void loadEntries(long[] data) {
		if(size + data.length > maxSize) {
			throw new IllegalArgumentException("Input data is greater than curretn size");
		}
		for(short i = 0; i < data.length; i++) {
			entries[size] = new DataEntry((short)size, data[i]);
			entries[size].setId(size + id * maxSize);
			size++;
		}
		sortEntry();
	}
	
	/**
	 * if already max size throw exception
	 * add a value to container
	 * @param value
	 */
	public void addEntry(long value) {
		if(isFull()) {
			throw new IllegalArgumentException("Input data is greater than curretn size");
		}
		entries[size] = new DataEntry((short)size, value);
		entries[size].setId(size + id * maxSize);
		size++;
	}
	
	/**
	 * sort entries
	 */
	public void sortEntry() {
		Arrays.sort(entries, new EntryValueComparator());
	}
	
	
	@Override
	public Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive) {
		
		//find left bound index and right bound index
		int left = search(fromInclusive ? fromValue - 0.5 : fromValue + 0.5);
		int right = search(toInclusive ? toValue + 0.5 : toValue - 0.5) - 1;
		PriorityQueue<DataEntry> pq = null;
		if(left <= right) {
			//Java under 1.8, PriorityQueue constructor must have capacity
			//using 1.8 no need this if condition
			pq = new PriorityQueue<DataEntry>(right - left + 1, new EntryIndexComparator());
			while(left <= right) {
				pq.add(entries[left++]);
			}
		}
		return new NumericIds(pq);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int getSize() {
		return maxSize;
	}
	
	public int getMaxSize() {
		return maxSize;
	}
	
	@Override
	public boolean isFull() {
		return size == maxSize;
	}
	
	//binary search find a double value index, or should insert index
	private int search(double val) {
		int l = 0;
		int r = size - 1;
		while(l <= r) {
			int mid = l + (r - l) / 2;
			if(entries[mid].getValue() == val) {
				return mid;
			} else if(entries[mid].getValue() < val) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
			
		}
		return l;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
		


}
