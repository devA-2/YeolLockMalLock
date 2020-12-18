package com.min.edu.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dto.LockerDTO;

@Service
public class ServiceImpl implements LockerIService {

	@Autowired
	private LockerIDao dao;
	
	@Override
	public List<LockerDTO> selectAll() {
		return dao.selectAll();
	}

}
