package com.baidu.zhixinzhoubian.net.response;

import java.util.List;

import com.baidu.zhixinzhoubian.model.PoiMessage;

public class PoiQueryResponse extends ResponseBase{

	private List<PoiMessage> mMessages = null;

	public List<PoiMessage> getmMessages() {
		return mMessages;
	}

	public void setmMessages(List<PoiMessage> mMessages) {
		this.mMessages = mMessages;
	}
	
}
