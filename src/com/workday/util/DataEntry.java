package com.workday.util;

public class DataEntry {
	
	private short index;
	private long value;
	private long id;
	
	public DataEntry() {
		
	}
	
	public DataEntry(short index, long value) {
		this.index = index;
		this.value = value;
	}
	
	public short getIndex() {
		return index;
	}
	public void setIndex(short index) {
		this.index = index;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
