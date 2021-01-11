package com.min.edu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LostPropertyDto {
	private String seq;
	private String receipt_user_id;
	private String cost_code;
	private String lost_regdate;
	private String lost_keep_location;
	private String lost_status;
	private String and_date;
	private String disposal_date;
}
