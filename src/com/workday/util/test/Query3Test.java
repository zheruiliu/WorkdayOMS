package com.workday.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.workday.util.CacheService;
import com.workday.util.Ids;
import com.workday.util.NumericCacheService;

public class Query3Test {

	
	@Test
	public void testQuery3RangeSize1() {
		CacheService cs = new NumericCacheService(1*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		Ids ids = cs.findIdsInRange(20, Long.MAX_VALUE, false, true);
		assertEquals(3, ids.nextKey());
		assertEquals(null, ids.nextEntry());
	}
	
	@Test
	public void testQuery3RangeSize2() {
		CacheService cs = new NumericCacheService(2*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		Ids ids = cs.findIdsInRange(20, Long.MAX_VALUE, false, true);
		assertEquals(3, ids.nextKey());
		assertEquals(null, ids.nextEntry());
	}
	
	@Test
	public void testQuery3RangeSize4() {
		CacheService cs = new NumericCacheService(4*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		Ids ids = cs.findIdsInRange(20, Long.MAX_VALUE, false, true);
		assertEquals(3, ids.nextKey());
		assertEquals(Ids.END_OF_IDS, ids.nextId());
	}
	
	@Test
	public void testQuery3RangeSize100() {
		CacheService cs = new NumericCacheService(100*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		Ids ids = cs.findIdsInRange(20, Long.MAX_VALUE, false, true);
		assertEquals(3, ids.nextKey());
		assertEquals(Ids.END_OF_IDS, ids.nextId());
	}
	
}
