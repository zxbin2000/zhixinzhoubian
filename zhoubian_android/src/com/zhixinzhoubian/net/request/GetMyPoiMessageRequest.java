package com.zhixinzhoubian.net.request;

import com.zhixinzhoubian.model.PoiMessage;

public class GetMyPoiMessageRequest {

	private long userId = -1;

	private int messageType = PoiMessage.MessageType.REQUIREMENT;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

}
