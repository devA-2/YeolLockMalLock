package com.dev2.ylml.model.dao;

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
		int result= sqlSession.insert("member.insertMember", dto);
		System.out.println(result);
		return (result>0)? true:false;

	}
	
	@Override
	public int idCheck(String email) {
		log.info("MemberDaoImpl idCheck : " + email);
		int result = sqlSession.selectOne("member.idCheck", email);
		System.out.println(result);
		return result;
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

	@Override
	public String idSearch(Map<String, Object> map) {
		log.info("MemberDaoImpl IdSearch : " + map);
		return sqlSession.selectOne("member.IdSearch", map);
	}

	@Override
	public int updateInfo(Map<String, Object> map) {
		int result= sqlSession.update("member.updateInfo", map);
		System.out.println(result);
		return result;
	}

//	@Override
//	public int updatePw(MemberDto dto) {
//		int result= sqlSession.update("member.updatePw", dto); // TODO : 암호화를 여기서 해줘야하는데 DTO로 받아서 처리를 해야 할듯?
//		return result;
//	}
	
	@Override
	public int updatePw(MemberDto dto) {
		String enPw = pwEncoder.encode(dto.getPw());
		dto.setPw(enPw);
		int result= sqlSession.update("member.updatePw", dto); // TODO : 암호화를 여기서 해줘야하는데 DTO로 받아서 처리를 해야 할듯?
		return result;
	}

	@Override
	public int usingCheck(String email) {
		int result = sqlSession.selectOne("member.usingCheck", email);
		return result;
	}

	@Override
	public boolean authUpdate(MemberDto dto) {
		int result = sqlSession.update("member.authUpdate", dto);
		return result>0?true:false;
	}

	@Override
	public int quitMember(String email) {
		int result = sqlSession.update("member.quitMember", email);
		return result;
	}
}
