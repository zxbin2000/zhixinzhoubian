package com.zhixinzhoubian.net.response;

import java.util.List;

import com.zhixinzhoubian.model.PoiMessage;

public class PoiQueryResponse extends ResponseBase{

	public List<PoiMessage> getMessages() {
		return Messages;
	}

	public void setMessages(List<PoiMessage> messages) {
		Messages = messages;
	}

	private List<PoiMessage> Messages = null;

}
