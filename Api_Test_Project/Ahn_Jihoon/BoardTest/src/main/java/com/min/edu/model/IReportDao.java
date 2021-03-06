package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.ReportDto;

public interface IReportDao {

	public boolean insertReport(ReportDto dto);

	public boolean replyReport(ReportDto dto);

	public boolean modifyReport(ReportDto dto);

	public List<ReportDto> selectAllReport();

	public List<ReportDto> selectDetailReport(String refer);

	public List<ReportDto> searchReport(String email);

	public boolean updateProcessStatus(ReportDto dto);
	
	public ReportDto selectDetail(String seq);
	
	public boolean reply(ReportDto dto);

}
