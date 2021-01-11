package com.min.edu.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.ReportDto;

@Repository
public class ISearchDaoImpl implements ISearchDao {

	private final String NS="com.min.edu.model.ISearchDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<ReportDto> searchId(String email) {
		return sqlSession.selectList(NS+"searchId", email);
	}

	@Override
	public boolean insertLostProperty(ReportDto dto) {
		// TODO Auto-generated method stub
		return false;
	}

}
