package com.workday.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.workday.util.CacheService;
import com.workday.util.Ids;
import com.workday.util.NumericCacheService;

public class Query1Test {

	@Test
	public void testQuery1RangeSize1() {
		CacheService cs = new NumericCacheService(1*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		Ids ids = cs.findIdsInRange(14, 17, true, true);
		assertEquals(2, ids.nextKey());
		assertEquals(5, ids.nextKey());
		assertEquals(6, ids.nextKey());
		assertEquals(null, ids.nextEntry());
	}
	
	@Test
	public void testQuery1RangeSize2() {
		CacheService cs = new NumericCacheService(2*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		Ids ids = cs.findIdsInRange(14, 17, true, true);
		assertEquals(2, ids.nextKey());
		assertEquals(5, ids.nextKey());
		assertEquals(6, ids.nextKey());
		assertEquals(null, ids.nextEntry());
	}
	
	@Test
	public void testQuery1RangeSize4() {
		CacheService cs = new NumericCacheService(4*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		Ids ids = cs.findIdsInRange(14, 17, true, true);
		assertEquals(2, ids.nextKey());
		assertEquals(5, ids.nextKey());
		assertEquals(6, ids.nextKey());
		assertEquals(Ids.END_OF_IDS, ids.nextId());
	}
	
	@Test
	public void testQuery1RangeSize100() {
		CacheService cs = new NumericCacheService(100*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		Ids ids = cs.findIdsInRange(14, 17, true, true);
		assertEquals(2, ids.nextKey());
		assertEquals(5, ids.nextKey());
		assertEquals(6, ids.nextKey());
		assertEquals(Ids.END_OF_IDS, ids.nextId());
	}
	
	/*
	private void printEntry(Ids ids) {
		DataEntry dataEntry = null;
		while((dataEntry = ids.nextEntry()) != null) {
			System.out.println(dataEntry.getId() + " :  " + dataEntry.getValue());
		}
	}*/

}
