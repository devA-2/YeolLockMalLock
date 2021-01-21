package com.min.edu.model;

import com.min.edu.dto.RFIDDto;

public interface RFIDIService {

	// ------------------------------- 물품 보관 ----------------------------- 
		/**
		 * 키 제외하고 물품부터 등록
		 * @param dto
		 * @return
		 */
		public boolean insertGoods(RFIDDto dto);
		
		/**
		 * 물품 보관함의 seq, 보관하는 사람의 email, 보관하는 사람의 TAG를 합쳐 키 생성하고 키 등록하기
		 * @param dto
		 * @return
		 */
		public boolean insertKey(RFIDDto dto);
		
	// ----------------------------- 키 전송 -----------------------------
		
		/**
		 * STORAGE_GOODS의 OUT_USER를 키의 수신자이자 보관함에서 물품을 꺼내는 사람의 email로 변경
		 * @param dto
		 * @return
		 */
		public boolean updateOutUser(RFIDDto dto);
		
		
		/**
		 * STORAGE_GOODS의 KEY를 보관함에서 물품을 꺼내는 사람의 KEY로 재설정해주기
		 * -> 보관함 seq + 보관함에서 물품 꺼내는 사람의 email + 보관함에서 물품 꺼내는 사람의 tag로 키 생성해서 update.
		 * @param dto
		 * @return
		 */
		public boolean updateKey(RFIDDto dto);
	
}
