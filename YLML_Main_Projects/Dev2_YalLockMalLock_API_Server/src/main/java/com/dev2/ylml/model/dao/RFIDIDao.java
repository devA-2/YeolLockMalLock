package com.dev2.ylml.model.dao;

import java.util.Map;

import com.dev2.ylml.dto.RFIDDto;

public interface RFIDIDao {
	
// ------------------------------- 물품 보관 ----------------------------- 
	
	// 2. 키 등록
	public boolean insertKey(Map<String, Object> map);
	
// ----------------------------- 키 전송 -----------------------------
	
	// 2. STORAGE_GOODS의 KEY를 받는 놈의 키로 재설정해주기
	public boolean updateKey(Map<String, Object> map);
	
}
