package src.main.java.com.dev2.ylml.model.dao;

import java.util.List;

import com.min.edu.dto.LostPropertyDto;

public interface LostPropertyIDao {

	public List<LostPropertyDto> selectAllLostProperty();
	
	public LostPropertyDto selectOneLostProperty(String seq);
}
