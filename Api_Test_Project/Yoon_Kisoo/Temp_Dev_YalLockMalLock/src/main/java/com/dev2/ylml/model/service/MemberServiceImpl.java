package com.dev2.ylml.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.MemberDto;
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
	public int phoneCheck(String phone_num) {
		return iDao.phoneCheck(phone_num);
	}

	@Override
	public MemberDto login(Map<String, Object> map) {
		return iDao.login(map);
	}

	@Override
	public String idSearch(Map<String, Object> map) {
		return iDao.idSearch(map);
	}

	@Override
	public int updateInfo(Map<String, Object> map) {
		return iDao.updateInfo(map);
	}

	@Override
	public int updatePw(Map<String, Object> map) {
		return iDao.updatePw(map);
	}

	@Override
	public int usingCheck(String email) {
		return iDao.usingCheck(email);
	}

	@Override
	public boolean emailAuth(MemberDto dto) {
		return iDao.emailAuth(dto);
	}
	
}
