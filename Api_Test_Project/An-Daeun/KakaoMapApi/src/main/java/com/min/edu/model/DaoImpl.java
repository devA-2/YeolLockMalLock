package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.LockerDTO;

@Repository
public class DaoImpl implements LockerIDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<LockerDTO> selectAll() {
		return sqlSession.selectList("lockerMapper.selectAll");
	}

}
