package com.dev2.ylml.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Manager_StorageDto implements Serializable{

	private static final long serialVersionUID = -16556474479128538L;
	
	private int boxSeq;
	private String storageId;
	private String storageName;
	private String address;
	private String subway;
	private String lat;
	private String lng;
	private String detail;
	private String manager;
	private String boxStatus;
	private String boxAmount;
	
	
}
