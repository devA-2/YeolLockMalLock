package com.min.edu.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.min.edu.vo.BoardVO;
import com.min.edu.vo.FileVO;

@Repository
public class BoardDaoImple implements IBoardDao {
	
private final String NS = "com.min.edu.dao.IBoardDao.";
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<BoardVO> boardList() {
		List<BoardVO> lists = sqlSession.selectList(NS+"boardList");
		return lists;
	}
	
	@Override
	public boolean writeBoard(BoardVO bdto) {
		int cnt = sqlSession.insert(NS+"writeBoard", bdto);
		return cnt>0?true:false;
	}

	@Override
	public boolean insertFile(FileVO fdto) {
		int cnt = sqlSession.insert(NS+"insertFile", fdto);
		return cnt>0?true:false;
	}

	@Override
	public BoardVO getOneBoard(String seq) {
		return sqlSession.selectOne(NS+"getOneBoard", seq);
	}

}
