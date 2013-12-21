package com.baidu.zhixinzhoubian.manager;

import android.os.Bundle;

public class BangbangEvent {


	public static final class EVENT_ID {

		public static final int ACTION_UPDATE_USER_INFO = 1;

		public static final int ACTION_PUBLISH_PIO_INFO = 2;

	}
	
	private int id = -1;

	private int returnCode = -1;

	private String message = null;
	
	private Bundle data = null;

	public BangbangEvent(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Bundle getData() {
		return data;
	}

	public void setData(Bundle data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "BangbangEvent [id=" + id + ", returnCode=" + returnCode + ", message=" + message + ", data=" + data
				+ "]";
	}
	
}
