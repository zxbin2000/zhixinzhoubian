package com.zhixinzhoubian.net.request;

import com.zhixinzhoubian.model.PoiMessage;

public class PublishInfomationRequest {

	private String userId = null;
	private String message = null;
	private int messageType = PoiMessage.MessageType.REQUIREMENT;
	
	private String photoPath = null;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
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

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String phonePath) {
		this.photoPath = phonePath;
	}

	public String getPoiTag() {
		return poiTag;
	}

	public void setPoiTag(String poiTag) {
		this.poiTag = poiTag;
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

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	private String poiTag = null;
	
	/**
	 * 经度
	 */
	private double langX = 0.0d;
	
	/**
	 * 纬度
	 */
	private double latY = 0.0d;
	
	private int distance = 0;
	
	/**
	 * 开始时间(yyyy-MM-dd HH:mm:ss)
	 */
	private String beginTime = "";
	
	/**
	 * 结束时间(yyyy-MM-dd HH:mm:ss)
	 */
	private String endTime = "";
	
	
}
