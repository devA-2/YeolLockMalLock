package com.dev2.ylml.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class StorageListDto implements Serializable{


	private static final long serialVersionUID = -1124979262935238701L;
	private String storageId;
	private String storageName;
	private String address;
	private String subway;
	private String detail;
	private String manager;
	private String lng;
	private String lat;
	private int cnt;
	
	
}
