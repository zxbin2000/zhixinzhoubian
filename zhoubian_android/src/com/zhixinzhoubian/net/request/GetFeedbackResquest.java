package com.zhixinzhoubian.net.request;



public class GetFeedbackResquest {

	private long userId = -1L;
	
	private String poiId = null;

	public GetFeedbackResquest(long userId, String poiId) {
		super();
		this.userId = userId;
		this.poiId = poiId;
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
	
	
	
}
