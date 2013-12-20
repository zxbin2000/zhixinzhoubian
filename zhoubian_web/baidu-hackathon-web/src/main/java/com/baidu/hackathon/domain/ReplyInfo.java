package com.baidu.hackathon.domain;

import java.util.Date;

public class ReplyInfo {
	private Integer replyId;
	private String poiId;
	private Integer userId;
	private String message;
	private Date timeline;
	
	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public String getPoiId() {
		return poiId;
	}
	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}
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
	public Date getTimeline() {
		return timeline;
	}
	public void setTimeline(Date timeline) {
		this.timeline = timeline;
	}
	
}