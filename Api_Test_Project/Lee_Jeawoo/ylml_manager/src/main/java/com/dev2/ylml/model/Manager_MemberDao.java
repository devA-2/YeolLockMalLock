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
		//TODO logger 찍기
		List<Manager_MemberDto> lists = null;
		lists = sqlSession.selectList(NS+"selectallDelivery");
		return lists;
	}
	
	// 임시권한 담당자 및 배송원 정보조회 id="selectTempDelivery"
	@Override
	public List<Manager_MemberDto> selectTempDelivery() {
		List<Manager_MemberDto> lists = null;
		lists = sqlSession.selectList(NS+"selectTempDelivery");
		return lists;
	}

	// 담당자 및 배송원 권한 부여 id="modifyAuth"
	@Override
	public boolean modifyAuth(Manager_MemberDto dto) {
		int cnt = sqlSession.update(NS+"modifyAuth", dto);
		return cnt>0?true:false;
	}

	// 담당자 및 배송원의 아이디를 통해 정보 조회 
	// (이메일, 이름, 핸드폰번호, 권한, 배송코드, 현재위치)
	@Override
	public Manager_MemberDto selectIdDelivery(String email) {
		Manager_MemberDto dto = sqlSession.selectOne(NS+"selectIdDelivery", email);
		return dto;
	}

	
	
}
