package com.android.cloudsearch.obj;

import java.util.ArrayList;
import java.util.List;

public class CloudSearchObj {
	public int status;
	public int total;
	public int size;
	public List<CloudItemObj> contents;
	public CloudSearchObj() {
		super();
		this.status = 0;
		this.total = 0;
		this.size = 0;
		this.contents = new ArrayList<CloudItemObj>();
	}
	@Override
	public String toString() {
		return "CloudSearchObj [status=" + status + ", total=" + total
				+ ", size=" + size + ", contents=" + contents + "]";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<CloudItemObj> getContents() {
		return contents;
	}
	public void setContents(List<CloudItemObj> contents) {
		this.contents = contents;
	}
}
