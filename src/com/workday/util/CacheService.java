package com.workday.util;

/**
 * Distribute Cache query system
 * 
 * Run time NlogL + RlogR
 * N is total container, L is container size, R is total query result
 * 
 * @author Zherui Liu
 *
 */

public interface CacheService {
	
	/**
	 * add data array to cache
	 */
	public void add(long[] data);
	
	
	/**
	 * search a range
	 * @param fromValue
	 * @param toValue
	 * @param fromInclusive
	 * @param toInclusive
	 * @return
	 */
	public Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive);
	
	
	/**
	 * total container in cache
	 * @return
	 */
	public int totalContainer();
}
