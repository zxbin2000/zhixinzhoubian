package com.baidu.zhixinzhoubian.net.request;

public class UpdateUserInfoRequest {

	/**
	 * 用户uid
	 */
	private long userId = -1L;
	
	/**
	 * 用户名称
	 */
	private String nickName = null;
	
	/**
	 * 用户电话
	 */
	private String telephone = null;
	
	/**
	 * 设备UUID
	 */
	private String diviceId = null;

	
	
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDiviceId() {
		return diviceId;
	}

	public void setDiviceId(String diviceId) {
		this.diviceId = diviceId;
	}
	
	
}
