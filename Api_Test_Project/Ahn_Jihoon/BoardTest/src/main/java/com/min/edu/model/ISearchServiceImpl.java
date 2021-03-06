package com.min.edu.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.LostPropertyDto;
import com.min.edu.dto.ReportDto;

@Service
public class ISearchServiceImpl implements ISearchService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ISearchDao iDao;
	
	@Override
	public List<ReportDto> searchId(String email) {
		log.info("--------------------- 신고글 검색(id) 실행 ---------------------");
		return iDao.searchId(email);
	}

	@Override
	public List<LostPropertyDto> searchId2(String receipt_user_id) {
		log.info("--------------------- 유실물 검색(id) 실행 ---------------------");
		return iDao.searchId2(receipt_user_id);
	}

	@Override
	public boolean insertLostProperty(ReportDto dto) {
		// TODO Auto-generated method stub
		return false;
	}
}
