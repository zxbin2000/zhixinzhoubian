package com.baidu.zhixinzhoubian.net.request;

/**
 * 兴趣订阅
 * @author guojialin
 *
 */
public class InterestSubscribeRequest {

	private long userId = -1;
	
	private int[] tagList = null;
	
	private int distance = -1;
	
	private int frequency = -1;
	

	public InterestSubscribeRequest(long userId, int[] tagList, int distance, int frequency) {
		super();
		this.userId = userId;
		this.tagList = tagList;
		this.distance = distance;
		this.frequency = frequency;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int[] getTagList() {
		return tagList;
	}

	public void setTagList(int[] tagList) {
		this.tagList = tagList;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
}
