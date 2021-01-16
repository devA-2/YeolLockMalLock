package com.dev2.ylml.model;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev2.ylml.dto.Manager_MemberDto;

@Service
public class ManagerLoginServiceImpl implements ManagerLoginIService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ManagerLoginIDao service;
	
	@Override
	public Manager_MemberDto loginMember(Map<String, Object> map) {
		logger.info("관리자 로그인 정보 : "+ map);
		
		return service.loginMember(map);
	}

}
