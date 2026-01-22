package com.bs.spring.model.dao.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.model.dto.Board;

public interface BoardDao {
	List<Board> selectBoardAll(SqlSession session, Map<String,Object> param);
	int insertBoard(SqlSession session, Board board);
	Board selectBoardByNo(SqlSession session, int no);
	int selectBoardCount(SqlSession session);
	int updateBoardReadcount(SqlSession session, int no);
}
