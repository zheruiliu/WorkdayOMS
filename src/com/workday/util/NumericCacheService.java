package com.workday.util;
import java.util.*;


public class NumericCacheService implements CacheService {
	
	private List<RangeContainer> containers;
	private int containerSize  = 32000 / 8;
	private RangeContainerFactory rangeFactory;
	private int containerCount;
	
	public NumericCacheService() {
		rangeFactory = new NumericRangeContainerFactory();
		containerCount = 0;
		containers = new ArrayList<RangeContainer>();
		containers.add(rangeFactory.createContainer(containerSize, containerCount++));
	}
	
	public NumericCacheService(int containerBytes) {
		this.containerSize = containerBytes / 8;
		rangeFactory = new NumericRangeContainerFactory();
		containerCount = 0;
		containers = new ArrayList<RangeContainer>();
		containers.add(rangeFactory.createContainer(containerSize, containerCount++));
	}

	@Override
	public void add(long[] data) {
		
		synchronized(this) {
			
			//add each value to a container
			for(int i = 0; i < data.length; i++) {
				writeToContainer(data[i]);
			}
			
			//if current container not full, sort current container
			if(!getCurrentContainer().isEmpty()) {
				getCurrentContainer().sortEntry();
			}
		}
		
		
	}
	
	
	private void writeToContainer(long value) {
		RangeContainer container = getCurrentContainer();
		container.addEntry(value);
		if(container.isFull()) {
			//if a container is full sort it, create a new container
			container.sortEntry();
			RangeContainer nContainer = rangeFactory.createContainer(containerSize, containerCount++);
			containers.add(nContainer);
		}
		
	}
	
	@Override
	public int totalContainer() {
		return getCurrentContainer().isEmpty() ? containerCount - 1 : containerCount;
	}
	
	private RangeContainer getCurrentContainer() {
		return containers.get(containers.size() - 1);
	}

	@Override
	public Ids findIdsInRange(long fromValue, long toValue, boolean fromInclusive, boolean toInclusive) {
		
		
		//get current result, not include new add
		int totalContainer = totalContainer();

		JoinIds joinIds = new JoinIds(totalContainer);
		
		for(int i = 0; i < totalContainer - 1; i++) {
			RunnableFinder rFinder = new RunnableFinder(containers.get(i));
			rFinder.setJoinIds(joinIds);
			rFinder.setSeachRange(fromValue, toValue, fromInclusive, toInclusive);
			Thread t = new Thread(rFinder);
			t.start();
		}
		
		//if is top container, need synchronized because if write to this container will cause problem
		synchronized(this) {
			RunnableFinder rFinder = new RunnableFinder(containers.get(totalContainer - 1));
			rFinder.setJoinIds(joinIds);
			rFinder.setSeachRange(fromValue, toValue, fromInclusive, toInclusive);
			Thread t = new Thread(rFinder);
			t.start();
			
		}
		
		joinIds.waitFinish();
		
		return joinIds;
	}
	
	

}
