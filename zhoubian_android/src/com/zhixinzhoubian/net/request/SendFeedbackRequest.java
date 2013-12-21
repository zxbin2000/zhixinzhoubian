package com.zhixinzhoubian.net.request;

public class SendFeedbackRequest {

	private long userId = -1L;

	private String poiId = null;

	private String message = null;

	public SendFeedbackRequest(long userId, String poiId, String message) {
		super();
		this.userId = userId;
		this.poiId = poiId;
		this.message = message;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPoiId() {
		return poiId;
	}

	public void setPoiId(String poiId) {
		this.poiId = poiId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
