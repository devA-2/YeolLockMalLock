package com.dev2.ylml.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberDto {
	
	private String tag;
	private String id_num;
	private String email;
	private String pw;
	private String name;
	private String phone_num;
	private int auth;
	private Date reg_date;
	private Date lev_date;
	private char del_flag;
	

}
