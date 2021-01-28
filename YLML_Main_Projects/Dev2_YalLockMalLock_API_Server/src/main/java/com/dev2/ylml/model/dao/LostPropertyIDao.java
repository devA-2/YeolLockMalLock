package com.dev2.ylml.model.dao;

import java.util.List;

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.util.PagingVO;


public interface LostPropertyIDao {

	public List<LostPropertyDto> selectAllLostProperty();
	
	public LostPropertyDto selectOneLostProperty(String seq);
	
	public boolean insertLostProperty(LostPropertyDto dto);
	
	public List<LostPropertyDto> selectInsertLostPropertyList(String storage_id);
	
	public List<LostPropertyDto> selectAllPagingLostProperty(PagingVO vo);
	
	public int countLostProperty();
}
