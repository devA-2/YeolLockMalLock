package com.dev2.ylml.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.Manager_MemberDto;

@Repository
public class ManagerLoginDao implements ManagerLoginIDao {

	private final String NS = "com.dev2.ylml.model.dao.ManagerLoginIDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Manager_MemberDto loginMember(Map<String, Object> map) {
		
		return sqlSession.selectOne(NS+"loginMember",map);
	}

}
