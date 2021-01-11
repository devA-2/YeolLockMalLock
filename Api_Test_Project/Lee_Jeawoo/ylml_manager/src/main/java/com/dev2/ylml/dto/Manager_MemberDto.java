package com.dev2.ylml.dto;

import java.io.Serializable;

public class Manager_MemberDto implements Serializable{

	private static final long serialVersionUID = 2790576328876297586L;

	public String email;
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
	
	public Manager_MemberDto() {
		super();
	}

	public Manager_MemberDto(String email, String name, String phone_num, String auth, String deliveryman_id,
			String delivery_code, String current_loc, String reg_date, String leave_date, String delivery_start,
			String delivery_arrive) {
		super();
		this.email = email;
		this.name = name;
		this.phone_num = phone_num;
		this.auth = auth;
		this.deliveryman_id = deliveryman_id;
		this.delivery_code = delivery_code;
		this.current_loc = current_loc;
		this.reg_date = reg_date;
		this.leave_date = leave_date;
		this.delivery_start = delivery_start;
		this.delivery_arrive = delivery_arrive;
	}

	@Override
	public String toString() {
		return "Manager_MemberDto [email=" + email + ", name=" + name + ", phone_num=" + phone_num + ", auth=" + auth
				+ ", deliveryman_id=" + deliveryman_id + ", delivery_code=" + delivery_code + ", current_loc="
				+ current_loc + ", reg_date=" + reg_date + ", leave_date=" + leave_date + ", delivery_start="
				+ delivery_start + ", delivery_arrive=" + delivery_arrive + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getDeliveryman_id() {
		return deliveryman_id;
	}

	public void setDeliveryman_id(String deliveryman_id) {
		this.deliveryman_id = deliveryman_id;
	}

	public String getDelivery_code() {
		return delivery_code;
	}

	public void setDelivery_code(String delivery_code) {
		this.delivery_code = delivery_code;
	}

	public String getCurrent_loc() {
		return current_loc;
	}

	public void setCurrent_loc(String current_loc) {
		this.current_loc = current_loc;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getLeave_date() {
		return leave_date;
	}

	public void setLeave_date(String leave_date) {
		this.leave_date = leave_date;
	}

	public String getDelivery_start() {
		return delivery_start;
	}

	public void setDelivery_start(String delivery_start) {
		this.delivery_start = delivery_start;
	}

	public String getDelivery_arrive() {
		return delivery_arrive;
	}

	public void setDelivery_arrive(String delivery_arrive) {
		this.delivery_arrive = delivery_arrive;
	}
	
	
	
	
	
	
	
	
}
