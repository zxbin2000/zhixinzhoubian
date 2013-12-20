package com.baidu.hackathon.domain;

import java.util.ArrayList;
import java.util.List;

public class CloudItemObj {
	public String title;
	public List<Double> location;
	public String city;
	public long create_time;
	public int geotable_id;
	public String tags;
	public String province;
	public String district;
	public long userId;
	public long endTime ;
	public long beginTime ;
	public int scope ;
	public String photoPath;
	public int messageType;
	public String message;
	public long uid;
	public int coord_type;
	public int type;
	public int distance;
	public int weight;
	public long feedbackId;
	
	public long getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}
	public CloudItemObj() {
		super();
		this.title = "";
		this.location = new ArrayList<Double>();
		this.city = "";
		this.create_time = 0;
		this.geotable_id = 0;
		this.tags = "";
		this.province = "";
		this.district = "";
		this.userId = 0;
		this.endTime = 0;
		this.beginTime = 0;
		this.scope = 0;
		this.photoPath = "";
		this.messageType = 0;
		this.message = "";
		this.uid = 0;
		this.coord_type = 0;
		this.type = 0;
		this.distance = 0;
		this.weight = 0;
		this.feedbackId = 0;
	}
	@Override
	public String toString() {
		return "CloudItemObj [title=" + title + ", location=" + location
				+ ", city=" + city + ", create_time=" + create_time
				+ ", geotable_id=" + geotable_id + ", tags=" + tags
				+ ", province=" + province + ", district=" + district
				+ ", userId=" + userId + ", endTime=" + endTime
				+ ", beginTime=" + beginTime + ", scope=" + scope
				+ ", photoPath=" + photoPath + ", messageType=" + messageType
				+ ", message=" + message + ", uid=" + uid + ", coord_type="
				+ coord_type + ", type=" + type + ", distance=" + distance
				+ ", weight=" + weight + ", feedbackId=" + feedbackId + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Double> getLocation() {
		return location;
	}
	public void setLocation(List<Double> location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getCreate_time() {
		return create_time;
	}
	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}
	public int getGeotable_id() {
		return geotable_id;
	}
	public void setGeotable_id(int geotable_id) {
		this.geotable_id = geotable_id;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	public int getScope() {
		return scope;
	}
	public void setScope(int scope) {
		this.scope = scope;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public int getCoord_type() {
		return coord_type;
	}
	public void setCoord_type(int coord_type) {
		this.coord_type = coord_type;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
