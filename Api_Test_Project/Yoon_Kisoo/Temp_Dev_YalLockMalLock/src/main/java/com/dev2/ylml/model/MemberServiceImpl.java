package com.dev2.ylml.model;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.MemberDto;


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
	public MemberDto IdSearch(Map<String, Object> map) {
		return iDao.IdSearch(map);
	}

	@Override
	public boolean updateInfo(Map<String, Object> map) {
		return iDao.updateInfo(map);
	}
	
}
