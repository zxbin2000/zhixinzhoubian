package com.baidu.zhixinzhoubian.net.response;

public class ResponseBase {

	protected int returnCode = 0;
	
	protected String message = "";
	

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
