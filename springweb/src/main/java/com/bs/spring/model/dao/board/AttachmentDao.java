package com.bs.spring.model.dao.board;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.model.dto.Attachment;

public interface AttachmentDao {
	int insertAttachment(SqlSession session, Attachment a);
	int deleteAttachment(SqlSession session, int no);
}
