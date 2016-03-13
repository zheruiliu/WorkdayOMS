package com.workday.util.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.workday.util.*;

public class CacheContainerSizeTest {

	@Test
	public void testTotalContainerSize1() {
		CacheService cs = new NumericCacheService(8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		assertEquals(cs.totalContainer(), 7);
	}
	
	@Test
	public void testTotalContainerSize2() {
		CacheService cs = new NumericCacheService(2*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		assertEquals(cs.totalContainer(), 4);
	}
	
	@Test
	public void testTotalContainerSize3() {
		CacheService cs = new NumericCacheService(3*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		assertEquals(cs.totalContainer(), 3);
	}
	
	@Test
	public void testTotalContainerSize7() {
		CacheService cs = new NumericCacheService(7*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		assertEquals(cs.totalContainer(), 1);
	}
	
	@Test
	public void testTotalContainerSize100() {
		CacheService cs = new NumericCacheService(100*8);
		cs.add(new long[]{10,12,17,21,2,15,16});
		assertEquals(cs.totalContainer(), 1);
	}
	
	@Test
	public void testTotalContainerSize0() {
		CacheService cs = new NumericCacheService(100*8);
		assertEquals(cs.totalContainer(), 0);
	}

}
