package com.dev2.ylml.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.dto.StorageGoodsDto;


@Repository
public class LostPropertyDao implements LostPropertyIDao {

	private final String NS="com.dev2.ylml.dao.LostPropertyIDao.";
	
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

	@Override
	public int insertLostProperty(List<StorageGoodsDto> list) {
		int cnt = sqlSession.insert(NS+"insertLostProperty", list);
		return cnt;
	}

	@Override
	public List<StorageGoodsDto> selectInsertLostPropertyList() {
		return sqlSession.selectList(NS+"selectInsertLostPropertyList");
	}

}
