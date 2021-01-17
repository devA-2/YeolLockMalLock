package com.dev2.ylml.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class MemberDto implements Serializable{

	private static final long serialVersionUID = 1465862168229624446L;
	private String tag;
	private String idNum;
	private String email;
	private String pw;
	private String name;
	private String phoneNum;
	private int auth;
	private String regDate;
	private String levDate;
	private String delFlag;
	
	
}
