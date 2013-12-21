package com.zhixinzhoubian.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FeedbackItem implements Parcelable{
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
		return "FeedbackItem [userId=" + userId + ", nickName=" + nickName
				+ ", message=" + message + ", time=" + time + "]";
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeLong(userId);
		dest.writeString(nickName);
		dest.writeString(message);
		dest.writeString(time);
	}
}
