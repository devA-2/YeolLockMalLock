package com.dev2.ylml.dto;

import java.io.Serializable;

public class StorageBoxListDto implements Serializable {

	private static final long serialVersionUID = 2914710415991543027L;
	
	String storageId;
	String storageName;
	String address;
	String subway;
	String detail;
	String manager;
	String lng;
	String lat;
	
	public StorageBoxListDto() {
		super();
	}

	@Override
	public String toString() {
		return "StorageBoxListDto [storageId=" + storageId + ", storageName=" + storageName + ", address=" + address
				+ ", subway=" + subway + ", detail=" + detail + ", manager=" + manager + ", lng=" + lng + ", lat=" + lat
				+ "]";
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSubway() {
		return subway;
	}

	public void setSubway(String subway) {
		this.subway = subway;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
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
