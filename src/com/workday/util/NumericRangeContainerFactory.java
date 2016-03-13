package com.workday.util;

public class NumericRangeContainerFactory implements RangeContainerFactory {
	
	
	@Override
	public RangeContainer createContainer(long[] data) {
		return new NumericRangeContainer(data);
	}
	

	@Override
	public RangeContainer createContainer(int size) {
		return new NumericRangeContainer(size);
	}


	@Override
	public RangeContainer createContainer(int maxSize, int id) {
		return new NumericRangeContainer(maxSize, id);
	}

}
