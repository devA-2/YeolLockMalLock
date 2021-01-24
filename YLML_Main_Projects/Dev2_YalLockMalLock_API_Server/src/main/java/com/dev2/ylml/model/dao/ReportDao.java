package com.dev2.ylml.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.util.PagingVO;


@Repository
public class ReportDao implements ReportIDao {

	private final String NS="com.dev2.ylml.dao.ReportIDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public boolean insertReport(ReportDto dto) {
		int cnt = sqlSession.insert(NS+"insertReport", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean replyReport(ReportDto dto) {
		int cnt = sqlSession.update(NS+"replyReport", dto);
		return cnt>0?true:false;
	}

	@Override
	public boolean modifyReport(ReportDto dto) {
		int cnt = sqlSession.update(NS+"modifyReport", dto);
		return cnt>0?true:false;
	}

	@Override
	public List<ReportDto> selectAllReport() {
		return sqlSession.selectList(NS+"selectAllReport");
	}

	@Override
	public List<ReportDto> selectDetailReport(String refer) {
		return sqlSession.selectList(NS+"selectDetailReport", refer);
	}

	@Override
	public List<ReportDto> searchReport(String email) {
		return sqlSession.selectList(NS+"searchReport", email);
	}

	@Override
	public boolean updateProcessStatus(ReportDto dto) {
		int cnt = sqlSession.update(NS+"updateProcessStatus", dto);
		return cnt>0?true:false;
	}

	@Override
	public ReportDto selectDetailGoReply(String seq) {
		return sqlSession.selectOne(NS+"selectDetail", seq);
	}

	@Override
	public boolean reply(ReportDto dto) {
		int cnt = sqlSession.insert(NS+"reply", dto);
		return cnt>0?true:false;
	}

	//페이징 
	
	@Override
	public int countReport() {
		return sqlSession.selectOne(NS+"countReport");
	}

	@Override
	public List<ReportDto> selectReport(PagingVO vo) {
		return sqlSession.selectList(NS+"selectReport",vo);
	}

}
