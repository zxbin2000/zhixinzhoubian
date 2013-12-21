package com.zhixinzhoubian.model;



import java.util.Arrays;

import android.os.Parcel;
import android.os.Parcelable;

public class PoiMessage implements Parcelable{

	public class MessageType{
		/**
		 * 需求
		 */
		public static final int REQUIREMENT = 1; 
		
		/**
		 *  服务
		 */
		public static final int SERVICE = 2;  
	}
	
	/**
	 * LBS数据ID
	 */
	private String poiId = null;
	
	/**
	 * 被响应需求PoiID
	 */
	private String feedback = null;
	
	/**
	 * 发送方用户ID
	 */
	private long userId = -1L;
	
	
	/**
	 * 发送方用户昵称
	 */
	private String nickName = null;
	
	/**
	 * 发送方填写的信息
	 */
	private String message = null;
	
	/**
	 * 信息类型
	 */
	private int messageType = MessageType.REQUIREMENT;
	
	/**
	 * 图片远程地址
	 */
	private String[] imageUrls = null;
	
	/**
	 * 图片本地地址
	 */
	private String[] imageLocalUrls = null;
	
	/**
	 * Poi 标签
	 */
	private int[] poiTag = null;
	
	/**
	 * 经度
	 */
	private double longX = 0.0d;
	/**
	 * 纬度
	 */
	private double latY = 0.0d;
	
	/**
	 * 距离
	 */
	private int distance = -1;
	
	/**
	 * 开始时间
	 */
	private String beginTime = null;
	
	/**
	 * 结束时间
	 */
	private String endTime = null;

	
	public String getPoiId() {
		return poiId;
	}

	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String[] getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String[] imageUrls) {
		this.imageUrls = imageUrls;
	}

	public int[] getPoiTag() {
		return poiTag;
	}

	public void setPoiTag(int[] poiTag) {
		this.poiTag = poiTag;
	}

	public double getLongX() {
		return longX;
	}

	public void setLongX(double longX) {
		this.longX = longX;
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

	public String[] getImageLocalUrls() {
		return imageLocalUrls;
	}

	public void setImageLocalUrls(String[] imageLocalUrls) {
		this.imageLocalUrls = imageLocalUrls;
	}

	@Override
	public String toString() {
		return "PoiMessage [poiId=" + poiId + ", feedback=" + feedback + ", userId=" + userId + ", nickName="
				+ nickName + ", message=" + message + ", messageType=" + messageType + ", imageUrls="
				+ Arrays.toString(imageUrls) + ", imageLocalUrls=" + Arrays.toString(imageLocalUrls) + ", poiTag="
				+ Arrays.toString(poiTag) + ", longX=" + longX + ", latY=" + latY + ", distance=" + distance
				+ ", beginTime=" + beginTime + ", endTime=" + endTime + "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(poiId);
		dest.writeString(feedback);
		dest.writeLong(userId);
		dest.writeString(nickName);
		dest.writeString(message);
		dest.writeInt(messageType);
		dest.writeArray(imageUrls);
		dest.writeIntArray(poiTag);
		dest.writeDouble(longX);
		dest.writeDouble(latY);
		dest.writeInt(distance);
		dest.writeString(beginTime);
		dest.writeString(endTime);

	}

}
