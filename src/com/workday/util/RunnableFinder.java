package com.workday.util;

public class RunnableFinder implements Runnable {
	
	private RangeContainer rangeContainer;
	private long fromValue, toValue;
	private boolean fromInclusive, toInclusive;
	private JoinIds joinIds;
	
	public RunnableFinder(RangeContainer rangeContainer) {
		this.rangeContainer = rangeContainer;
	}

	@Override
	public void run() {
		Ids ids = rangeContainer.findIdsInRange(fromValue, toValue, fromInclusive, toInclusive);
		DataEntry dataEntry = null;
		while((dataEntry = ids.nextEntry()) != null) {
			joinIds.add(dataEntry);
		}
		joinIds.finishOne();
	}
	
	public void setSeachRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive) {
		this.fromValue = fromValue;
		this.toValue = toValue;
		this.fromInclusive = fromInclusive;
		this.toInclusive = toInclusive;
	}
	
	public void setJoinIds(Ids ids) {
		this.joinIds = (JoinIds)ids;
	}

}
