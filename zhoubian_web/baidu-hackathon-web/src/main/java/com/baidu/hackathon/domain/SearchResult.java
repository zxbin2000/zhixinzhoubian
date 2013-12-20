package com.baidu.hackathon.domain;

import com.baidu.hackathon.search.common.Location;

public class SearchResult {

	/**
	 * @param args
	 */
	public String name;
	public SearchResult() {
		super();
		this.name = "";
		this.location = new Location();
		this.address = "";
		this.street_id = "'";
		this.telephone = "";
		this.uid = "";
		this.detail_info = new SearchDetailInfo();
	}

	public Location location;
	public String address;
	public String street_id;
	public String telephone;
	public String uid;
	public SearchDetailInfo detail_info;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public SearchDetailInfo getDetail_info() {
		return detail_info;
	}

	public void setDetail_info(SearchDetailInfo detail_info) {
		this.detail_info = detail_info;
	}

	@Override
	public String toString() {
		return "Record [name=" + name + ", location=" + location + ", address="
				+ address + ", street_id=" + street_id + ", telephone="
				+ telephone + ", uid=" + uid + ", detail_info=" + detail_info
				+ "]";
	}
}
