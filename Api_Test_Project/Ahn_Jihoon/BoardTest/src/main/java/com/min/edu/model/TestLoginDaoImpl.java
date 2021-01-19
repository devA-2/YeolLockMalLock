package com.min.edu.model;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.MemberDto;

@Repository
public class TestLoginDaoImpl implements TestILoginDao {

	private final String NS="com.min.edu.model.TestLogin.";
	
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public MemberDto loginMember(Map<String, Object> map) {
		return sqlSession.selectOne(NS+"loginMember", map);
	}



}
