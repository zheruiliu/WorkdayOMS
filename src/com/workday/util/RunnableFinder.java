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
	
	
	/**
	 * set query to this finder
	 * @param fromValue
	 * @param toValue
	 * @param fromInclusive
	 * @param toInclusive
	 */
	public void setSeachRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive) {
		this.fromValue = fromValue;
		this.toValue = toValue;
		this.fromInclusive = fromInclusive;
		this.toInclusive = toInclusive;
	}
	

	public RangeContainer getRangeContainer() {
		return rangeContainer;
	}

	public void setRangeContainer(RangeContainer rangeContainer) {
		this.rangeContainer = rangeContainer;
	}

	public long getFromValue() {
		return fromValue;
	}

	public void setFromValue(long fromValue) {
		this.fromValue = fromValue;
	}

	public long getToValue() {
		return toValue;
	}

	public void setToValue(long toValue) {
		this.toValue = toValue;
	}

	public boolean isFromInclusive() {
		return fromInclusive;
	}

	public void setFromInclusive(boolean fromInclusive) {
		this.fromInclusive = fromInclusive;
	}

	public boolean isToInclusive() {
		return toInclusive;
	}

	public void setToInclusive(boolean toInclusive) {
		this.toInclusive = toInclusive;
	}

	public JoinIds getJoinIds() {
		return joinIds;
	}

	public void setJoinIds(JoinIds joinIds) {
		this.joinIds = joinIds;
	}

}
