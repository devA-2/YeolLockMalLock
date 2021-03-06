package com.dev2.ylml.model.dao;

import java.util.List;

import com.dev2.ylml.dto.ReportDto;
import com.dev2.ylml.util.PagingVO;


public interface ReportIDao {

	public boolean insertReport(ReportDto dto);

	public boolean replyReport(ReportDto dto);

	public boolean modifyReport(ReportDto dto);

	public List<ReportDto> selectAllReport();

	public List<ReportDto> selectDetailReport(String refer);

	public List<ReportDto> searchReport(String email);

	public boolean updateProcessStatus(ReportDto dto);
	
	public ReportDto selectDetailGoReply(String seq);
	
	public boolean reply(ReportDto dto);

	//페이징 관련
	public int countReport();
	
	public List<ReportDto> selectReport(PagingVO vo);
}
