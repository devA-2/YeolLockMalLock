package com.dev2.ylml.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.Manager_MemberDto;

@Service
public class Manager_ServiceImpl implements Manager_IService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Manager_MemberIDao service;
	
	@Override
	public List<Manager_MemberDto> selectallDelivery() {
		logger.info("담당자 및 배송원 전체 정보조회 selectDelivery {}");
		return service.selectallDelivery();
	}

	@Override
	public List<Manager_MemberDto> selectTempDelivery() {
		logger.info("임시권한 담당자 및 배송원 정보조회 selectTempDelivery {}");
		return service.selectTempDelivery();
	}

	@Override
	public boolean modifyAuth(Manager_MemberDto dto) {
		logger.info("담당자 및 배송원 권한 부여 modifyAuth {}");
		return service.modifyAuth(dto);
	}

	@Override
	public Manager_MemberDto selectIdDelivery(String email) {
		logger.info("담당자 및 배송원의 아이디를 통해 정보 조회 selectIdDelivery {}");
		return service.selectIdDelivery(email);
	}

}
