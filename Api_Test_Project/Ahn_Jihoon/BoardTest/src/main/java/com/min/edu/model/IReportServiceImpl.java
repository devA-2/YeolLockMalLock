package com.min.edu.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.ReportDto;

@Service
public class IReportServiceImpl implements IReportService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private IReportDao iDao;
	
	@Override
	public boolean insertReport(ReportDto dto) {
		log.info("신고 글 입력 : {} ", dto);
		return iDao.insertReport(dto);
	}

	@Override
	public boolean replyReport(ReportDto dto) {
		log.info("답변 글 입력 : {} ", dto);
		boolean isc = iDao.replyReport(dto);
		return isc?true:false;
	}
	
	@Override
	public boolean reply(ReportDto dto) {
		log.info("답변 글 달기 실행 : {} ", dto);
		return iDao.reply(dto);
	}

	@Override
	public boolean modifyReport(ReportDto dto) {
		log.info("신고 글 수정 : {} ", dto);
		return iDao.modifyReport(dto);
	}

	@Override
	public List<ReportDto> selectAllReport() {
		log.info("전체 글 조회 : {} ");
		return iDao.selectAllReport();
	}

	@Override
	public List<ReportDto> selectDetailReport(String refer) {
		log.info("상세 글 조회 : {} ", refer);
		return iDao.selectDetailReport(refer);
	}

//	@Override
//	public List<ReportDto> searchReport(String email) {
//		log.info("아이디로 신고 글 검색 : {} ", email);
//		return iDao.searchReport(email);
//	}

	@Override
	public boolean updateProcessStatus(ReportDto dto) {
		log.info("처리 상태 변경 : {} ", dto);
		return iDao.updateProcessStatus(dto);
	}

	@Override
	public ReportDto selectDetail(String seq) {
		log.info("답변 달 상세 페이지 이동 : {} ", seq);
		return iDao.selectDetail(seq);
	}


}
