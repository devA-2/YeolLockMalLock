package com.min.edu.dto;

import java.io.Serializable;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 2348443588796997560L;
	
	private String email;
	private String phone;
	private String name;
	private String rfid;
	private String password;
	private String sns;
	private String regdate;
	
	public UserDto() {
		super();
	}

	@Override
	public String toString() {
		return "NaverDto [email=" + email + ", phone=" + phone + ", name=" + name + ", rfid=" + rfid + ", password="
				+ password + ", sns=" + sns + ", regdate=" + regdate + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSns() {
		return sns;
	}

	public void setSns(String sns) {
		this.sns = sns;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
