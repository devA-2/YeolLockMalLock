package com.min.edu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.vo.KakaoVo;

@Service
public class KakaoServiceImpl implements KakaoService {
	
	@Autowired
	private KakaoDao iDao;

	@Override
	public KakaoVo selectAll() {
		return iDao.selectAll();
	}

}
