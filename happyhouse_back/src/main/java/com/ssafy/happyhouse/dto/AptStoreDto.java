package com.ssafy.happyhouse.dto;

public class AptStoreDto {
	private int storeno;
	private String storename;
	private String categoryL;
	private String categoryS;
	private String dongcode;
	private String dongname;
	private String address;
	private String lng;
	private String lat;
	private float distance;
	
	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "AptStoreInfoDto [storeno=" + storeno + ", storename=" + storename + ", categoryL=" + categoryL
				+ ", categoryS=" + categoryS + ", dongcode=" + dongcode + ", dongname=" + dongname + ", address="
				+ address + ", lng=" + lng + ", lat=" + lat + "]";
	}
	
	public int getStoreno() {
		return storeno;
	}
	public void setStoreno(int storeno) {
		this.storeno = storeno;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getCategoryL() {
		return categoryL;
	}
	public void setCategoryL(String categoryL) {
		this.categoryL = categoryL;
	}
	public String getCategoryS() {
		return categoryS;
	}
	public void setCategoryS(String categoryS) {
		this.categoryS = categoryS;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		this.dongname = dongname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
}
