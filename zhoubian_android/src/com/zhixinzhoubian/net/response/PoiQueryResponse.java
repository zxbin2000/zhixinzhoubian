package com.zhixinzhoubian.net.response;

import java.util.List;

import com.zhixinzhoubian.model.PoiMessage;

public class PoiQueryResponse extends ResponseBase{

	private List<PoiMessage> mMessages = null;

	public List<PoiMessage> getmMessages() {
		return mMessages;
	}

	public void setmMessages(List<PoiMessage> mMessages) {
		this.mMessages = mMessages;
	}
	
}
