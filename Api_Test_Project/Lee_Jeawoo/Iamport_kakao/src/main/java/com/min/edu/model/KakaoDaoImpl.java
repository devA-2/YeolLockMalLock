package com.min.edu.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.KakaoVo;

@Repository
public class KakaoDaoImpl implements KakaoDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String NS = "com.min.edu.model.KakaoDao.";
	
	@Autowired
	private SqlSessionTemplate sqlsession;

	@Override
	public KakaoVo selectAll() {
		logger.info("KakaoDaoImpl selectAll");
		return sqlsession.selectOne(NS+"selectAll");
	}

}
