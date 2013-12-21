package com.baidu.zhixinzhoubian.model;

public class User {

	private long userId = -1L;
	private String telephone = null;
	private String nickName = null;
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", telephone=" + telephone + ", nickName=" + nickName + "]";
	}
	
}
