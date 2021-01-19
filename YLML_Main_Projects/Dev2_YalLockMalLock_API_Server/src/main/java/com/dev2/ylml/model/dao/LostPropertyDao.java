package com.dev2.ylml.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.LostPropertyDto;


@Repository
public class LostPropertyDao implements LostPropertyIDao {

	private final String NS="com.min.edu.model.ILostPropertyDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<LostPropertyDto> selectAllLostProperty() {
		return sqlSession.selectList(NS+"selectAllLostProperty");
	}

	@Override
	public LostPropertyDto selectOneLostProperty(String seq) {
		return sqlSession.selectOne(NS+"selectOneLostProperty", seq);
	}

}
