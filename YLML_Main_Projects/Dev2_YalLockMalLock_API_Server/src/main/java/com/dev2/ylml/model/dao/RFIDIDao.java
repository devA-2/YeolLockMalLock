package com.dev2.ylml.model.dao;

import com.dev2.ylml.dto.RFIDDto;

public interface RFIDIDao {
	
// ------------------------------- 물품 보관 ----------------------------- 
	// 1. 키 제외하고 물품부터 등록
	public boolean insertGoods(RFIDDto dto);
	
	// 2. 키 등록
	public boolean insertKey(RFIDDto dto);
	
// ----------------------------- 키 전송 -----------------------------
	// 1. STORAGE_GOODS의 OUT_USER를 키를 받는사람의 email로 변경
	public boolean updateOutUser(RFIDDto dto);
	
	// 2. STORAGE_GOODS의 KEY를 받는 놈의 키로 재설정해주기
	public boolean updateKey(RFIDDto dto);
	
}
