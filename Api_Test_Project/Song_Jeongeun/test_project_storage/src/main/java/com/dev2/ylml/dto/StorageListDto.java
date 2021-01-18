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
public class StorageListDto implements Serializable {

	private static final long serialVersionUID = 2914710415991543027L;
	
	String storageId;
	String storageName;
	String address;
	String subway;
	String detail;
	String manager;
	String lng;
	String lat;
	
}
