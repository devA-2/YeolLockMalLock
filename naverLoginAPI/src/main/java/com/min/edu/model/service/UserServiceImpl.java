package com.min.edu.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.UserDto;
import com.min.edu.model.dao.UserIDao;

@Service
public class UserServiceImpl implements UserIService {

	@Autowired
	private UserIDao dao;
	
	@Override
	public List<UserDto> memList() {
		return dao.memList();
	}

	@Override
	public boolean signupMember(UserDto dto) {
		return dao.signupMember(dto);
	}

	@Override
	public boolean signupNaver(UserDto dto) {
		return dao.signupNaver(dto);
	}

	@Override
	public boolean emailCheck(String email) {
		return dao.emailCheck(email);
	}

	@Override
	public UserDto loginMember(Map<String, Object> map) {
		return dao.loginMember(map);
	}

}
