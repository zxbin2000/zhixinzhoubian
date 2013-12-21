package com.zhixinzhoubian.net.request;

import com.zhixinzhoubian.model.PoiMessage;

public class PublishInfomationRequest {

	private String userId = null;
	private String message = null;
	private int messageType = PoiMessage.MessageType.REQUIREMENT;
	
	private String phonePath = null;
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
