package com.android.search.obj;

import java.util.ArrayList;
import java.util.List;

public class SearchQueryObject {

	/**
	 * @param args
	 */
	public int status;
	public String message;
	public int total;
	public List<SearchResult> results;

	public SearchQueryObject() {
		super();
		this.status = 0;
		this.message = "";
		this.total = 0;
		this.results = new ArrayList<SearchResult>();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<SearchResult> getResults() {
		return results;
	}

	public void setResults(List<SearchResult> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return "searchQueryObject [status=" + status + ", message=" + message
				+ ", total=" + total + ", results=" + results + "]";
	}
}
