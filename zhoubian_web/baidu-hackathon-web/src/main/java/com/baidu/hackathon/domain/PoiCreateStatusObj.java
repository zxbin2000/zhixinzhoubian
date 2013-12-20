package com.baidu.hackathon.domain;

public class PoiCreateStatusObj {
	public String status;
	public String id;
	public String message;
	public PoiCreateStatusObj() {
		super();
		this.status = "";
		this.id = "";
		this.message = "";
	}
	@Override
	public String toString() {
		return "PoiCreateObj [status=" + status + ", id=" + id + ", message="
				+ message + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
