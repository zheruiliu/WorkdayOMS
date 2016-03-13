package com.workday.util;

public interface RangeContainer {

	/**
	* @return the Ids of all instances found in the container that
	* have data value between fromValue and toValue with optional
	* inclusivity. The ids should be returned in ascending order when retrieved
	* using nextId().
	*/
	Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive);
	

	
	/**
	 * get current size of range
	 */
	int getSize();
	
	/**
	 * check if range is full
	 * @return
	 */
	boolean isFull();
	
	/**
	 * add a record to range
	 * @param value
	 */
	public void addEntry(long value);
	
	/**
	 * load a array of data together then sort
	 * if data array size is greater than max throw exception
	 * @param data
	 */
	public void loadEntries(long[] data);
	
	
	/**
	 * sort current range
	 */
	public void sortEntry();
	
	
	/**
	 * return if range is empty
	 * @return
	 */
	public boolean isEmpty();
	
	/**
	 * get container Id
	 * @return
	 */
	public int getId();
	
	
	
}
