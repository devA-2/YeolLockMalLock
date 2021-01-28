package com.dev2.ylml.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LostPropertyDto {
	private String seq;
	private String receiptUserId;
	private String costCode;
	private String lostRegdate;
	private String lostKeepLocation;
	private String lostStatus;
	private String andDate;
	private String disposalDate;
	private String storageId;
}
