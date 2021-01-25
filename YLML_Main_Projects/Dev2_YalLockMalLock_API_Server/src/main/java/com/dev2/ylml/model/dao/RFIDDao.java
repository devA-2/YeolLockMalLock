package com.dev2.ylml.model.dao;


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
	public boolean insertKey(RFIDDto dto) {
		int cnt = sqlSession.update(NS+"insertKey", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean updateKey(RFIDDto dto) {
		int cnt = sqlSession.update(NS+"updateKey", dto);
		return cnt>0?true:false;
	}

}
