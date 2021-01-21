package com.dev2.ylml.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RFIDDto {

	// STORAGE_GOODS
	private String storageId    ;
	private String outUser      ;
	private String message       ;
	private String key           ;
	private String inUser       ;
	private String inTime       ;
	private String extendCnt    ;
	private String exTime       ;
	private String deliveryCode ;
	private String costCode     ;
	private String categoryCode ;
	private String boxSeq       ;

}