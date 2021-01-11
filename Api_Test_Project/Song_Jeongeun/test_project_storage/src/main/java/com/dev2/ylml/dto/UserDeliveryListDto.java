package com.dev2.ylml.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDeliveryListDto implements Serializable {

	private static final long serialVersionUID = -7900537746350142992L;

	int boxSeq;
	String storageName;
	String subway;
	String detail;
	String deliverymanId;
	String deliveryArrive;
	
}
