package com.dev2.ylml.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class StorageBoxDto implements Serializable{
	

	private static final long serialVersionUID = 1049299673225466744L;
	private int boxSeq;
	private String storageId;
	private String boxStatus;
	
}
