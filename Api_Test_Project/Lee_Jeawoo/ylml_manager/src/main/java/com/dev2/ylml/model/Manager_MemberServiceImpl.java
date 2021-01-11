package com.dev2.ylml.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev2.ylml.dto.Manager_MemberDto;

@Service
public class Manager_MemberServiceImpl implements Manager_MemberIService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Manager_MemberIDao service;
	
	// 담당자 및 배송원 전체 정보조회 id="selectDelivery" 
	@Override
	public List<Manager_MemberDto> selectallDelivery() {
		logger.info("담당자 및 배송원 전체 정보조회 selectDelivery {}");
		return service.selectallDelivery();
	}
	
	// 상세 정보조회 
	@Override
	public Manager_MemberDto selectDetail(String email) {
		logger.info("담당자 및 배송원 상세조회 selectDetail {}");
		Manager_MemberDto dto = service.selectDetail(email);
		return dto;
	}

	// 배송 정보조회 
	@Override
	public Manager_MemberDto DeliveryInfo(String email) {
		logger.info("담당자 및 배송원 배송 정보조회 DeliveryInfo {}");
		Manager_MemberDto dto = service.DeliveryInfo(email);
		return dto;
	}
	
	// 임시권한(89,99)을 가진 담당지 및 배송원 조회
	@Override
	public List<Manager_MemberDto> selectTempDelivery() {
		logger.info("임시권한 담당자 및 배송원 정보조회 selectTempDelivery {}");
		return service.selectTempDelivery();
	}

	// 담당자 및 배송원 권한 부여
	@Override
	public boolean modifyAuth(Manager_MemberDto dto) {
		logger.info("담당자 및 배송원 권한 부여 modifyAuth {}");
		return service.modifyAuth(dto);
	}

	// 담당자 및 배송원의 아이디를 통해 정보 조회
	@Override
	public Manager_MemberDto selectIdDelivery(String email) {
		logger.info("담당자 및 배송원의 아이디를 통해 정보 조회 selectIdDelivery {}");
		return service.selectIdDelivery(email);
	}

	

	

}
