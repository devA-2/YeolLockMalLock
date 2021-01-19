package com.dev2.ylml.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.MemberDto;
import com.dev2.ylml.dto.UserGoodsDto;
import com.dev2.ylml.model.dao.MemberIDao;


@Service
public class MemberServiceImpl implements MemberIService {
	
	@Autowired
	private MemberIDao iDao;

	@Override
	public boolean insertMember(MemberDto dto) {
		return iDao.insertMember(dto);
	}

	@Override
	public int idCheck(String email) {
		return iDao.idCheck(email);
	}

	@Override
	public int phoneCheck(String phoneNum) {
		return iDao.phoneCheck(phoneNum);
	}

	@Override
	public MemberDto login(Map<String, Object> map) {
		return iDao.login(map);
	}
	
	@Override
	public MemberDto apiLogin(Map<String, Object> map) {
		return iDao.apiLogin(map);
	}

	@Override
	public String idSearch(Map<String, Object> map) {
		return iDao.idSearch(map);
	}
	
	@Override
	public int pwSearch(Map<String, String> map) {
		return iDao.pwSearch(map);
	}

	@Override
	public int updateInfo(Map<String, Object> map) {
		return iDao.updateInfo(map);
	}

	@Override
	public int updatePw(MemberDto dto) {
		return iDao.updatePw(dto);
	}

	@Override
	public int usingCheck(String email) {
		return iDao.usingCheck(email);
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
		return iDao.authUpdate(dto);
	}

	@Override
	public int quitMember(String email) {
		return iDao.quitMember(email);
	}
	
	@Override
	public List<MemberDto> selectAll(String email) {
		return iDao.selectAll(email);
	}

	@Override
	public List<String> memberIdSearch() {
		return iDao.memberIdSearch();
	}
	@Override
	public MemberDto detailMember(String email) {
		return iDao.detailMember(email);
	}

	@Override
	public List<UserGoodsDto> memberUsing(String email) {
		return iDao.memberUsing(email);
	}

	
}
