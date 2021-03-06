package com.dev2.ylml.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.Manager_MemberDto;

@Repository
public class Manager_MemberDao implements Manager_MemberIDao {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final String NS = "com.dev2.ylml.model.Manager_MemberIDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 담당자 및 배송원 전체 정보조회 id="selectDelivery" 
	@Override
	public List<Manager_MemberDto> selectallDelivery() {
		logger.info("Daoimpl + selectallDelivery");
		List<Manager_MemberDto> lists = null;
		lists = sqlSession.selectList(NS+"selectallDelivery");
		return lists;
	}
	
	// 상세 정보조회 id="selectDetail"
	@Override
	public Manager_MemberDto selectDetail(String email) {
		logger.info("Daoimpl + selectDetail");
		Manager_MemberDto dto = sqlSession.selectOne(NS+"selectDetail", email); 
		return dto;
	}
	// 배송 정보조회 id="DeliveryInfo"
	@Override
	public Manager_MemberDto DeliveryInfo(String email) {
		logger.info("Daoimpl + DeliveryInfo");
		Manager_MemberDto dto = sqlSession.selectOne(NS+"DeliveryInfo", email);
		return dto;
	}
	
	
	
	
	// 임시권한 담당자 및 배송원 정보조회 id="selectTempDelivery"
	@Override
	public List<Manager_MemberDto> selectTempDelivery() {
		logger.info("Daoimpl + selectTempDelivery");
		List<Manager_MemberDto> lists = null;
		lists = sqlSession.selectList(NS+"selectTempDelivery");
		return lists;
	}

	// 담당자 및 배송원의 임시권한 -> 정식권한 id="modifyAuth"
	// AUTH 89 -> 80, 99 -> 90
	@Override
	public boolean modifyAuth(String email) {
		logger.info("Daoimpl + modifyAuth");
		int cnt = sqlSession.update(NS+"modifyAuth", email);
		return cnt>0?true:false;
	}

	// 담당자 및 배송원의 아이디를 통해 정보 조회 
	// (이메일, 이름, 핸드폰번호, 권한, 배송코드, 현재위치)
	@Override
	public Manager_MemberDto selectIdDelivery(String email) {
		logger.info("Daoimpl + selectIdDelivery");
		Manager_MemberDto dto = sqlSession.selectOne(NS+"selectIdDelivery", email);
		return dto;
	}

	

	
	
}
