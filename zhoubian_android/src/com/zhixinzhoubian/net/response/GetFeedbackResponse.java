package com.zhixinzhoubian.net.response;

import java.util.List;
import com.zhixinzhoubian.model.FeedbackItem;

public class GetFeedbackResponse extends ResponseBase{
	
	List<FeedbackItem> messages = null;

	public List<FeedbackItem> getMessages() {
		return messages;
	}

	public void setMessages(List<FeedbackItem> messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "GetFeedbackResponse [messages=" + messages + ", returnCode="
				+ returnCode + ", message=" + message + "]";
	}
	
	
}
