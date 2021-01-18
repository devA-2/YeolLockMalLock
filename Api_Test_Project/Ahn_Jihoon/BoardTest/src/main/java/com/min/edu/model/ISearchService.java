package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.LostPropertyDto;
import com.min.edu.dto.ReportDto;

public interface ISearchService {

	/** 신고 글을 email로 검색
	 * @param email
	 * @return
	 */
	public List<ReportDto> searchId(String email); // 신고글 검색
	
	/** 유실물 글을 email로 검색
	 * @param receipt_user_id
	 * @return
	 */
	public List<LostPropertyDto> searchId2(String receipt_user_id); // 유실물 검색
	
	/** 유실물 입력
	 * @param dto
	 * @return
	 */
	public boolean insertLostProperty(ReportDto dto);
	
}