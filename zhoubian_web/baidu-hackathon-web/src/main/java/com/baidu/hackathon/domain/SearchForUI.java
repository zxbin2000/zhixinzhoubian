package com.baidu.hackathon.domain;

import java.util.ArrayList;
import java.util.List;

public class SearchForUI {

	/**
	 * @param args
	 */
	public int code;
	public SearchForUI() {
		super();
		this.code = 0;
		this.message = "";
		this.data = new ArrayList<SearchForUIData>();
	}
	@Override
	public String toString() {
		return "SearchForUI [Code=" + code + ", message=" + message + ", data="
				+ data + "]";
	}
	public String message;
	public List<SearchForUIData> data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<SearchForUIData> getData() {
		return data;
	}
	public void setData(List<SearchForUIData> data) {
		this.data = data;
	}	
}
