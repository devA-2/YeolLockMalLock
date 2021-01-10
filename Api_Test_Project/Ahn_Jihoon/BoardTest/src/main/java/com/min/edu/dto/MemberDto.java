package com.min.edu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
	private String email    ;
	private String auth     ;
	private String id_num   ;
	private String pw       ;
	private String name     ;
	private String phone_num;
	private String reg_date ;
	private String lev_date ;
	private String del_flag ;
	private String tag      ;
}
