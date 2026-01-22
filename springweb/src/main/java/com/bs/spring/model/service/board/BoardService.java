package com.bs.spring.model.service.board;

import java.util.List;
import java.util.Map;

import com.bs.spring.model.dto.Board;

public interface BoardService {
	
	List<Board> searchBoardAll(Map<String,Object> param);
	int insertBoard(Board board);
	Board searchBoardByNo(int no, boolean flag);
	int searchBoardCount();
	
	
}
