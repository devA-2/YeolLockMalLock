package com.min.edu.model;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.RFIDDto;


@Repository
public class RFIDDao implements RFIDIDao {
	
	private final String NS="com.min.edu.model.RFIDIDao.";

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public boolean insertGoods(RFIDDto dto) {
		int cnt = sqlSession.insert(NS+"insertGoods", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean insertKey(RFIDDto dto) {
		int cnt = sqlSession.update(NS+"insertKey", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean updateOutUser(RFIDDto dto) {
		int cnt = sqlSession.update(NS+"updateOutUser", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean updateKey(RFIDDto dto) {
		int cnt = sqlSession.update(NS+"updateKey", dto);
		return cnt>0?true:false;
	}

}
