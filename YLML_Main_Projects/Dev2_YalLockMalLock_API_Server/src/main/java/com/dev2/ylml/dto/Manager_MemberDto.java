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
	public String phone_num;
	public String auth;
	public String deliveryman_id;
	public String delivery_code;
	public String current_loc;
	public String reg_date;
	public String leave_date;
	public String delivery_start;
	public String delivery_arrive;
	
	
}
