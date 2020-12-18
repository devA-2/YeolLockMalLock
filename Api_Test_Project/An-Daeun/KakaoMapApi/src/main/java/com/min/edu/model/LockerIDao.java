package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.LockerDTO;


public interface LockerIDao {

	public List<LockerDTO> selectAll();
}
