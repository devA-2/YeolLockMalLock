package com.dev2.ylml.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Manager_StorageDto {

	private int box_seq;
	private String storage_id;
	private String storage_name;
	private String address;
	private String subway;
	private String lat;
	private String lng;
	private String detail;
	private String manager;
	private String box_status;
	private String box_amount;
	
	
}
