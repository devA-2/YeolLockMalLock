package com.dev2.ylml.dto;

import java.io.Serializable;
import java.util.Date;

public class UserGoodsDto implements Serializable{


	private static final long serialVersionUID = -1740590824033288713L;
	private int boxSeq,extCnt;
	private String stroageId, key, inUser,outUser,deleiverCode,categoryCode,costCode,message;
	private Date inTime,exTime;
}
