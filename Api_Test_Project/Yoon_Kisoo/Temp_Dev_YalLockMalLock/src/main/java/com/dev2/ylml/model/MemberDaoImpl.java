package com.dev2.ylml.model;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.MemberDto;


@Repository
public class MemberDaoImpl implements MemberIDao {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private PasswordEncoder pwEncoder;

	@Override
	public boolean insertMember(MemberDto dto) {
		log.info("MemberDaoImpl insertMember : " + dto);
		String enPw = pwEncoder.encode(dto.getPw());
		dto.setPw(enPw);
		int cnt= sqlSession.insert("member.insertMember", dto);
		System.out.println(cnt);
		return (cnt>0)? true:false;
//		return sqlSession.insert("member.insertMember", dto)>0 ? true:false;

	}

	@Override
	public int idCheck(String email) {
		log.info("MemberDaoImpl idCheck : " + email);
		int result = sqlSession.selectOne("member.idCheck", email);
		System.out.println(result);
		return result;
//		return sqlSession.selectOne("member.idCheck", email);
	}

	@Override
	public int phoneCheck(String phone_num) {
		log.info("MemberDaoImpl phoneCheck : " + phone_num);
		int result = sqlSession.selectOne("member.phoneCheck", phone_num);
		return result;
	}

	@Override
	public MemberDto login(Map<String, Object> map) {
		log.info("MemberDaoImpl login : " + map);		
		MemberDto dto = null;
		String enPw = pwEncoder.encode((String) map.get("pw"));
		String dbPw = sqlSession.selectOne("member.enPw", map.get("email"));
		
		if(pwEncoder.matches((String) map.get("pw"), dbPw)) {
			dto = sqlSession.selectOne("member.enLogin",map);
		}
		return dto;
	}
	
	
	
	

}
