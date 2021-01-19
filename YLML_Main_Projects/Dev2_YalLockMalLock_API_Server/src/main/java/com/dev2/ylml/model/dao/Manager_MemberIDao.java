package com.dev2.ylml.model.dao;

import java.util.List;

import com.dev2.ylml.dto.Manager_MemberDto;

public interface Manager_MemberIDao {

	
	/** 담당자 및 배송원 전체 정보조회
	 * @return
	 */
	public List<Manager_MemberDto> selectallDelivery();
	
	/** 상세 정보조회
	 * @param email
	 * @return
	 */
	public Manager_MemberDto selectDetail(String email);
	
	/** 배송 정보조회
	 * @param email
	 * @return
	 */
	public Manager_MemberDto DeliveryInfo(String email);
	
	
	/**임시권한 담당자 및 배송원 정보조회
	 * @return
	 */
	public List<Manager_MemberDto> selectTempDelivery();
	
	/** 담당자 및 배송원 임시권한 변경
	 * @param dto
	 * @return
	 */
	public boolean modifyAuth(String email);
	
	/** 담당자 및 배송원의 아이디를 통해 정보 조회
	 * @param email
	 * @return
	 */
	public Manager_MemberDto selectIdDelivery(String email);
}
