package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.ReportDto;

public interface IReportService {

	public boolean insertReport(ReportDto dto);

	public boolean replyReport(ReportDto dto);

	public boolean modifyReport(ReportDto dto);

	public List<ReportDto> selectAllReport();

	public ReportDto selectOneReport(String seq);

	public List<ReportDto> searchReport(String email);

	public boolean updateProcessStatus(ReportDto dto);
	
}
