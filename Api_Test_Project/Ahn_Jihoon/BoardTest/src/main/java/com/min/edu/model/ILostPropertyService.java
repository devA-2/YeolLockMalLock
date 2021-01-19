package com.min.edu.model;

import java.util.List;

import com.min.edu.dto.LostPropertyDto;

public interface ILostPropertyService {

	/** 유실물 전체 리스트 조회
	 * @return
	 */
	public List<LostPropertyDto> selectAllLostProperty();
	
	/** 유실물 상세 조회
	 * @param seq
	 * @return
	 */
	public LostPropertyDto selectOneLostProperty(String seq);
}
