package com.dev2.ylml.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.StorageGoodsDto;


@Repository
public class MemberDao implements MemberIDao {
	
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
	public int phoneCheck(String phoneNum) {
		log.info("MemberDaoImpl phoneCheck : " + phoneNum);
		int result = sqlSession.selectOne("member.phoneCheck", phoneNum);
		return result;
	}

	@Override
	public MemberDto login(Map<String, Object> map) {
		log.info("MemberDaoImpl login : " + map);		
		return sqlSession.selectOne("member.enLogin",map);
	}
	
	@Override
	public MemberDto apiLogin(Map<String, Object> map) {
		log.info("MemberDaoImpl apiLogin : " + map);		
		return sqlSession.selectOne("member.enLogin",map);
	}
	
	@Override
	public MemberDto delLogin(Map<String, Object> map) {
		log.info("MemberDaoImpl delLogin : " + map);		
		return sqlSession.selectOne("member.enDelLogin",map);
	}
	
	@Override
	public MemberDto adminLogin(Map<String, Object> map) {
		log.info("MemberDaoImpl adminLogin : " + map);		
		return sqlSession.selectOne("member.enAdminLogin",map);
	}
	
	
	@Override
	public boolean enPw(Map<String, Object> map) {
		String dbPw = sqlSession.selectOne("member.enPw", map.get("email"));
		System.out.println("@@@@@@@@@@@@@" + dbPw);
		return pwEncoder.matches((String) map.get("pw"), dbPw);
	}
	

	@Override
	public String idSearch(Map<String, Object> map) {
		log.info("MemberDaoImpl IdSearch : " + map);
		return sqlSession.selectOne("member.IdSearch", map);
	}
	
	@Override
	public int pwSearch(Map<String, String> map) {
		int result = sqlSession.selectOne("member.pwSearch", map); 
		log.info("MemberDaoImpl pwSearch : " + map);
		return result;
	}

	@Override
	public int updateInfo(Map<String, Object> map) {
		int result= sqlSession.update("member.updateInfo", map);
		System.out.println(result);
		return result;
	}

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
		switch (dto.getAuth()) {
		case 19 : dto.setAuth(10); 
			break;
		case 89 : dto.setAuth(80); 
			break;
		case 99 : dto.setAuth(90); 
			break;
		}
		int result = sqlSession.update("member.authUpdate", dto);
		return result>0?true:false;
	}

	@Override
	public int quitMember(String email) {
		int result = sqlSession.update("member.quitMember", email);
		return result;
	}
	
	@Override
	public List<MemberDto> selectAll(Map<String, Object> map) {
		return sqlSession.selectList("member.selectAll",map);
	}
	@Override
	public int countMember(String email) {
		return sqlSession.selectOne("member.countMember",email);
	}
	@Override
	public List<String> memberIdSearch() {
		return sqlSession.selectList("member.memberIdSearch");
	}
	@Override
	public MemberDto detailMember(String email) {
		return sqlSession.selectOne("member.detailMember",email);
	}

	@Override
	public List<StorageGoodsDto> memberUsing(String email) {
		return sqlSession.selectList("member.memberUsing",email);
	}

	


}
