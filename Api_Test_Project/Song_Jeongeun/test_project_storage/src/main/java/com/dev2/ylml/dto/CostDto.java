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
public class CostDto implements Serializable {

	private static final long serialVersionUID = -5788213552908313867L;

	String costCode;
	int cost;
	String costStatus;
	
}
