package com.dev2.ylml.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Manager_MemberDto implements Serializable{

	private static final long serialVersionUID = 2790576328876297586L;

	public String email;
	public String pw;
	public String name;
	public String phoneNum;
	public String auth;
	public String deliverymanId;
	public String deliveryCode;
	public String currentLoc;
	public String regDate;
	public String levDate;
	public String deliveryStart;
	public String deliveryArrive;
	
	
}
