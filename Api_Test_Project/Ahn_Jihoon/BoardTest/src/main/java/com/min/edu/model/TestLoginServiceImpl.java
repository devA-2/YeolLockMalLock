package com.min.edu.model;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.MemberDto;

@Service
public class TestLoginServiceImpl implements TestILoginService {

	@Autowired
	private TestILoginDao iDao;
	
	@Override
	public MemberDto loginMember(Map<String, Object> map) {
		return iDao.loginMember(map);
	}



	
	
}
