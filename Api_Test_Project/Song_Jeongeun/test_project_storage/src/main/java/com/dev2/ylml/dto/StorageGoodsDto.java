package com.dev2.ylml.dto;

import java.io.Serializable;

public class StorageGoodsDto implements Serializable {

	private static final long serialVersionUID = -3510467039431284849L;
	
	int boxSeq;
	String storageId;
	String key;
	String inUser;
	String outUser;
	String inTime;
	String exTime;
	String deliveryCode;
	String categoryCode;
	String costCode;
	String message;
	int extendCnt;
	String overTime;
	
	public StorageGoodsDto() {
		super();
	}
	
	@Override
	public String toString() {
		return "StorageGoodsDto [boxSeq=" + boxSeq + ", storageId=" + storageId + ", key=" + key + ", inUser=" + inUser
				+ ", outUser=" + outUser + ", inTime=" + inTime + ", exTime=" + exTime + ", deliveryCode="
				+ deliveryCode + ", categoryCode=" + categoryCode + ", costCode=" + costCode + ", message=" + message
				+ ", extendCnt=" + extendCnt + ", overTime=" + overTime + "]";
	}

	public int getBoxSeq() {
		return boxSeq;
	}

	public void setBoxSeq(int boxSeq) {
		this.boxSeq = boxSeq;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getInUser() {
		return inUser;
	}

	public void setInUser(String inUser) {
		this.inUser = inUser;
	}

	public String getOutUser() {
		return outUser;
	}

	public void setOutUser(String outUser) {
		this.outUser = outUser;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getExTime() {
		return exTime;
	}

	public void setExTime(String exTime) {
		this.exTime = exTime;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCostCode() {
		return costCode;
	}

	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getExtendCnt() {
		return extendCnt;
	}

	public void setExtendCnt(int extendCnt) {
		this.extendCnt = extendCnt;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	
}
