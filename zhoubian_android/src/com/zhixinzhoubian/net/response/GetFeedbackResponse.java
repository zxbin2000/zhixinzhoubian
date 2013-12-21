package com.zhixinzhoubian.net.response;

public class GetFeedbackResponse extends ResponseBase{

	private long userId = -1L;
	
	private String nickName = null;
	
	private String message = null;
	
	private String time = null;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	
	@Override
	public String toString() {
		return "GetFeedbackResponse [userId=" + userId + ", nickName=" + nickName + ", message=" + message + ", time="
				+ time + ", returnCode=" + returnCode + "]";
	}
	
}
