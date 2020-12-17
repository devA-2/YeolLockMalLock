package com.min.edu.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.UserDto;

@Repository
public class UserDaoImple implements UserIDao {

	private final String NS = "com.min.edu.model.dao.UserIDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<UserDto> memList() {
		List<UserDto> lists = sqlSession.selectList(NS+"memberList");
		return lists;
	}

	@Override
	public boolean signupMember(UserDto dto) {
		int cnt = sqlSession.insert(NS+"signupMember", dto);
		return (cnt>0)?true:false;
	}

	@Override
	public boolean signupNaver(UserDto dto) {
		int cnt = sqlSession.insert(NS+"signupNaver", dto);
		return (cnt>0)?true:false;
	}

	@Override
	public boolean emailCheck(String email) {
		int cnt = sqlSession.selectOne(NS+"emailCheck", email);
		return (cnt>0)?true:false;
	}

	@Override
	public UserDto loginMember(Map<String, Object> map) {
		UserDto dto = sqlSession.selectOne(NS+"loginMember", map);
		return dto;
	}
}
