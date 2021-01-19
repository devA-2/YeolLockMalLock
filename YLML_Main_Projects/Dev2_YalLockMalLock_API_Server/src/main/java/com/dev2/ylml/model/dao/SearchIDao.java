package com.dev2.ylml.model.dao;

import java.util.List;

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.dto.ReportDto;


public interface SearchIDao {

	public List<ReportDto> searchId(String email); // 신고글 검색
	
	public List<LostPropertyDto> searchId2(String receipt_user_id); // 유실물 검색
	
	public boolean insertLostProperty(ReportDto dto);
	
}
