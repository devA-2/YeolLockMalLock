package com.dev2.ylml.model.dao;


import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.RFIDDto;



@Repository
public class RFIDDao implements RFIDIDao {
	
	private final String NS="com.dev2.ylml.model.dao.RFIDIDao.";

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public boolean insertKey(Map<String, Object> map) {
		int cnt = sqlSession.update(NS+"insertKey", map);
		return cnt>0?true:false;
	}

	@Override
	public boolean updateKey(Map<String, Object> map) {
		int cnt = sqlSession.update(NS+"updateKey", map);
		return cnt>0?true:false;
	}

}
