package com.baidu.hackathon.domain;

public class SubscibeInfoJson {
	private Integer userId;
	private String[] tagList;
	private Integer distance;
	private Integer frequency;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String[] getTagList() {
		return tagList;
	}
	public void setTagList(String[] tagList) {
		this.tagList = tagList;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

}