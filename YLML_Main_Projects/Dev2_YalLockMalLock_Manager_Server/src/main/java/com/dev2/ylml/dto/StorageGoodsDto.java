package com.dev2.ylml.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
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
	int cost;
	
}
