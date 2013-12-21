package com.baidu.hackathon.domain;

public class PoiSearchParam {

	private String userId;
	private Double lngX;
	private Double latY;
	private int distance;
	private int[] tagList;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getLngX() {
		return lngX;
	}
	public void setLngX(Double lngX) {
		this.lngX = lngX;
	}
	public Double getLatY() {
		return latY;
	}
	public void setLatY(Double latY) {
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
