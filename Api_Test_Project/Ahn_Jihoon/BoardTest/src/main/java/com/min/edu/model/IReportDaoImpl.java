package com.min.edu.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.ReportDto;

@Repository
public class IReportDaoImpl implements IReportDao {

	private final String NS="com.min.edu.model.IReportDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSessionFactoryBean;
	
	@Override
	public boolean insertReport(ReportDto dto) {
		int cnt = sqlSessionFactoryBean.insert(NS+"insertReport", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean replyReport(ReportDto dto) {
		int cnt = sqlSessionFactoryBean.update(NS+"replyReport", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean modifyReport(ReportDto dto) {
		int cnt = sqlSessionFactoryBean.update(NS+"modifyReport", dto);
		return cnt>0?true:false;
	}

	@Override
	public List<ReportDto> selectAllReport() {
		return sqlSessionFactoryBean.selectList(NS+"selectAllReport");
	}

	@Override
	public ReportDto selectOneReport(String seq) {
		return sqlSessionFactoryBean.selectOne(NS+"selectOneReport", seq);
	}

	@Override
	public List<ReportDto> searchReport(String email) {
		return sqlSessionFactoryBean.selectList(NS+"searchReport", email);
	}

	@Override
	public boolean updateProcessStatus(ReportDto dto) {
		int cnt = sqlSessionFactoryBean.update(NS+"updateProcessStatus", dto);
		return cnt>0?true:false;
	}

}
