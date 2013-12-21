package com.baidu.zhixinzhoubian.net.response;

import java.util.List;

import com.baidu.zhixinzhoubian.model.PoiMessage;

public class GetMyPoiMessageResponse extends ResponseBase{

	/**
	 * 返回发布的信息
	 */
	private List<PoiMessage> messages = null;

	public List<PoiMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<PoiMessage> messages) {
		this.messages = messages;
	}
	
}
