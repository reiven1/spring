package com.bs.spring.model.dao.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.model.dto.Attachment;

@Repository
public class AttachmentDaoImpl implements AttachmentDao {

	@Override
	public int insertAttachment(SqlSession session, Attachment a) {
		// TODO Auto-generated method stub
		return session.insert("board.insertAttach", a);
	}

	@Override
	public int deleteAttachment(SqlSession session, int no) {
		// TODO Auto-generated method stub
		return session.delete("board.deleteAttach", no);
	}

}
