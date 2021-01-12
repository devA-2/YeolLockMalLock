package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.LostPropertyDto;
import com.min.edu.dto.ReportDto;

public interface ISearchService {

	public List<ReportDto> searchId(String email); // 신고글 검색
	
	public List<LostPropertyDto> searchId2(String receipt_user_id); // 유실물 검색
	
	public boolean insertLostProperty(ReportDto dto);
	
}