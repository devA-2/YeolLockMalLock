package com.dev2.ylml.model.dao;

import java.util.List;

import com.dev2.ylml.dto.LostPropertyDto;
import com.dev2.ylml.util.PagingVO;
import com.dev2.ylml.dto.StorageGoodsDto;



public interface LostPropertyIDao {

	public List<LostPropertyDto> selectAllLostProperty();
	
	public LostPropertyDto selectOneLostProperty(String seq);
	
	public int insertLostProperty(List<StorageGoodsDto> list);
	
	public List<LostPropertyDto> selectAllPagingLostProperty(PagingVO vo);
	
	public int countLostProperty();

	public List<StorageGoodsDto> selectInsertLostPropertyList();

}
