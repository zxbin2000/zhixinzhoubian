package com.baidu.hackathon.domain;

import java.util.Date;

public class MessageInfoJson {
	private Integer userId;
	private String message;
	private int messageType;
	private String[] photoPath;
	private Integer poiTag;
	private Double lngX;
	private Double latY;
	private int distance;
	private Date beginTime;
	private Date endTime;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public String[] getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String[] photoPath) {
		this.photoPath = photoPath;
	}
	public Integer getPoiTag() {
		return poiTag;
	}
	public void setPoiTag(Integer poiTag) {
		this.poiTag = poiTag;
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
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}