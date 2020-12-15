package com.min.edu.service;

import java.util.List;

import com.min.edu.vo.BoardVO;
import com.min.edu.vo.FileVO;

public interface IBoardService {
	
	/**
	 * 전체 글 조회
	 */
	public List<BoardVO> boardList();
	
	/**
	 * 글 입력
	 */
	public boolean writeBoard(BoardVO bdto);
	public boolean insertFile(FileVO fdto);
	
	/**
	 * 상세글 조회
	 */
	public BoardVO getOneBoard(String seq);
	
}
