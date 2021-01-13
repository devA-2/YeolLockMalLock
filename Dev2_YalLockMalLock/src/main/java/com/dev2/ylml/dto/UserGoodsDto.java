package com.dev2.ylml.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class UserGoodsDto implements Serializable{

	private static final long serialVersionUID = -1740590824033288713L;
	private int boxSeq,extCnt;
	private String storageId, key, inUser,outUser,deleiveCode,categoryCode,costCode,message;
	private Date inTime,exTime;
}
