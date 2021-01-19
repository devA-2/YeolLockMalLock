package src.main.java.com.dev2.ylml.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.dto.ReportDto;

@Repository
public class ReportDao implements IReportDao {

	private final String NS="com.min.edu.model.IReportDao.";
	
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
	public ReportDto selectDetail(String seq) {
		return sqlSession.selectOne(NS+"selectDetail", seq);
	}

	@Override
	public boolean reply(ReportDto dto) {
		int cnt = sqlSession.insert(NS+"reply", dto);
		return cnt>0?true:false;
	}

}
