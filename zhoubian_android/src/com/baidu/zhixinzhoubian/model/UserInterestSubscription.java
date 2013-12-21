package com.baidu.zhixinzhoubian.model;

/**
 * 用户兴趣订阅类
 * 
 * @author guojialin
 *
 */
public class UserInterestSubscription {

	private int[] interestTags = null;

	private int distance = -1;

	private int requency = -1; // 更新频率 in minutes

	public int[] getInterestTags() {
		return interestTags;
	}

	public void setInterestTags(int[] interestTags) {
		this.interestTags = interestTags;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getRequency() {
		return requency;
	}

	public void setRequency(int requency) {
		this.requency = requency;
	}

}
