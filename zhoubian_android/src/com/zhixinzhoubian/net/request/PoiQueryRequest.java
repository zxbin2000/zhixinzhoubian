package com.zhixinzhoubian.net.request;

public class PoiQueryRequest {

	private long userId = -1L;

	private double langX = 0.0d;

	private double latY = 0.0d;

	private int distance = -1;

	private int[] tagList = null;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getLangX() {
		return langX;
	}

	public void setLangX(double langX) {
		this.langX = langX;
	}

	public double getLatY() {
		return latY;
	}

	public void setLatY(double latY) {
		this.latY = latY;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int[] getTagList() {
		return tagList;
	}

	public void setTagList(int[] tagList) {
		this.tagList = tagList;
	}

}
