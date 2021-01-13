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
public class UserStorageListDto implements Serializable {
	
	private static final long serialVersionUID = -938667966245847681L;
	
	int boxSeq, cost, extendCnt, overTime, overH, overM, overCost;
	String storageId, storageName, subway, detail, inUser, outUser, costCode, inTime, exTime, categoryCode;
	
}
