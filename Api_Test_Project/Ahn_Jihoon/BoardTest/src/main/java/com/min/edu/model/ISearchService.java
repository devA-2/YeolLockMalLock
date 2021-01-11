package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.ReportDto;

public interface ISearchService {

	public List<ReportDto> searchId(String email);
	
	public boolean insertLostProperty(ReportDto dto);
	
}