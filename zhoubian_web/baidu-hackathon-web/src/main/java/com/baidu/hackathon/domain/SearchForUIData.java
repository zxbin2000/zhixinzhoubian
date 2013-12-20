package com.baidu.hackathon.domain;

public class SearchForUIData {

	/**
	 * @param args
	 */
	String poiId;
	public String getPoiId() {
		return poiId;
	}
	@Override
	public String toString() {
		return "SearchForUIData [poiId=" + poiId + ", feedbackId=" + feedbackId
				+ ", userId=" + userId + ", nickName=" + nickName
				+ ", Message=" + Message + ", messageType=" + messageType
				+ ", photoPath=" + photoPath + ", poiTag=" + poiTag + ", lngX="
				+ lngX + ", latY=" + latY + ", distance=" + distance
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
	}
	public SearchForUIData() {
		super();
		this.poiId = "";
		this.feedbackId = "";
		this.userId = "";
		this.nickName = "";
		this.Message = "";
		this.messageType = 2;
		this.photoPath = "";
		this.poiTag = "";
		this.lngX = 0;
		this.latY = 0;
		this.distance = 0;
		this.beginTime = "";
		this.endTime = "";
	}
	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}
	public String getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
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
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getPoiTag() {
		return poiTag;
	}
	public void setPoiTag(String poiTag) {
		this.poiTag = poiTag;
	}
	public double getLngX() {
		return lngX;
	}
	public void setLngX(double lngX) {
		this.lngX = lngX;
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
	String feedbackId;
	String userId;
	String nickName;
	String Message;
	int messageType;
	String photoPath;
	String poiTag;
	double lngX;
	double latY;
	int distance;
	String beginTime;
	String endTime;
	
	
}
