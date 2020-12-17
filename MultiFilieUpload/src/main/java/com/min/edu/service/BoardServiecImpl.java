package com.min.edu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.edu.dao.IBoardDao;
import com.min.edu.vo.BoardVO;
import com.min.edu.vo.FileVO;

@Service
public class BoardServiecImpl implements IBoardService {
	
	@Autowired
	private IBoardDao dao;

	@Override
	public List<BoardVO> boardList() {
		return dao.boardList();
	}
	
	@Override
	public boolean writeBoard(BoardVO bdto) {
		return dao.writeBoard(bdto);
	}

	@Override
	public boolean insertFile(FileVO fdto) {
		return dao.insertFile(fdto);
	}

	@Override
	public BoardVO getOneBoard(String seq) {
		return dao.getOneBoard(seq);
	}

}
