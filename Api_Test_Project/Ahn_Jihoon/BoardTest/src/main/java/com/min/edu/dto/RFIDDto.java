package com.min.edu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RFIDDto {

	// STORAGE_GOODS
	private String storage_id    ;
	private String out_user      ;
	private String message       ;
	private String key           ;
	private String in_user       ;
	private String in_time       ;
	private String extend_cnt    ;
	private String ex_time       ;
	private String delivery_code ;
	private String cost_code     ;
	private String category_code ;
	private String box_seq       ;

}