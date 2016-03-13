package com.workday.util;

public interface Ids {

	/**
	* return the next id in sequence, -­1 if at end of data.
	* The ids should be in sorted order (from lower to higher) to facilitate
	* the query distribution into multiple containers.
	*/
	static final short END_OF_IDS = -1;
	short nextId();
	
	/**
	 * nextKey used in gloabl data retriew, short is too short for all ids
	 * @return
	 */
	long nextKey();
	
	/**
	 * get next enty which contains value field
	 * @return
	 */
	DataEntry nextEntry();
}
