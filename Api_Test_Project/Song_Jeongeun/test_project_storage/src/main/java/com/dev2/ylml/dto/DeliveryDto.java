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
public class DeliveryDto implements Serializable {

	private static final long serialVersionUID = -6756611299836807370L;
	
	String deliveryCode;
	int deliveryCost;
	String deliverymanId;
	String outboxId;
	String deliveryArrive;
	
}
