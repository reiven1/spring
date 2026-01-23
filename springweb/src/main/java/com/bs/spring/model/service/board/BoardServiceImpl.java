package com.bs.spring.model.service.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.model.dao.board.AttachmentDao;
import com.bs.spring.model.dao.board.BoardDao;
import com.bs.spring.model.dto.Attachment;
import com.bs.spring.model.dto.Board;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final SqlSession session;
	private final BoardDao dao;
	private final AttachmentDao attachDao;

	@Override
	public List<Board> searchBoardAll(Map<String, Object> param) {
		return dao.selectBoardAll(session, param);
	}

	@Override
	@Transactional
	public int insertBoard(Board board) {
		int result = 0;
		try {
			result = dao.insertBoard(session, board);
			if (result > 0 && board.getFiles().size() > 0) {
				for (Attachment a : board.getFiles()) {
					a.setBoardRef(board.getBoardNo());
					result = attachDao.insertAttachment(session, a);
				}
			}

		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new RuntimeException("저장 실패");
		}

		return result;
	}

	@Override
	public Board searchBoardByNo(int no, boolean flag) {
		Board b = dao.selectBoardByNo(session, no);
		if (b != null && !flag) {
			dao.updateBoardReadcount(session, no);
		}
		return b;
	}

	@Override
	public int searchBoardCount() {
		return dao.selectBoardCount(session);
	}

}
