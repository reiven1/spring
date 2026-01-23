package com.bs.spring.model.dao.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.common.aop.MyAnnotation;
import com.bs.spring.model.dto.Board;
@Repository
public class BoardDaoImpl implements BoardDao {
	
	@MyAnnotation
	@Override
	public List<Board> selectBoardAll(SqlSession session, Map<String, Object> param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		
		RowBounds rowBounds
				=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("board.selectAll",param,rowBounds);
	}

	@Override
	public int insertBoard(SqlSession session, Board board) {
		return session.insert("board.insertBoard",board);
	}

	@Override
	public Board selectBoardByNo(SqlSession session, int no) {
		return session.selectOne("board.selectByNo",no);
	}
	@Override
	public int selectBoardCount(SqlSession session) {
		return session.selectOne("board.selectCount");
	}

	@Override
	public int updateBoardReadcount(SqlSession session, int no) {
		return session.update("board.updateBoardReadcount",no);
	}

	
}
