package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.LostPropertyDto;

public interface ILostPropertyService {

	public List<LostPropertyDto> selectAllLostProperty();
	
	public LostPropertyDto selectOneLostProperty(String seq);
}
