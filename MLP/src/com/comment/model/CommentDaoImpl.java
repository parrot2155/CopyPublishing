package com.comment.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.comment.dto.CommentDto;

public class CommentDaoImpl implements CommentDao {

	@Override
	public int insertComment(CommentDto dto) {
	    SqlSession session = SqlMapConfig.getSqlSession();
	    int res = session.insert("comment.insertComment", dto); 
	    if (res > 0) session.commit();
	    session.close();
	    return res;
	}

    @Override
    public List<CommentDto> selectComments(int feedId) {
        SqlSession session = SqlMapConfig.getSqlSession();
        List<CommentDto> list = session.selectList("comment.selectComments", feedId);
        session.close();
        return list;
    }    

    @Override
    public int deleteComment(int commentId) {
        SqlSession session = SqlMapConfig.getSqlSession();
        int res = session.delete("comment.deleteComment", commentId);
        if (res > 0) session.commit();
        session.close();
        return res;
    }

    @Override
    public int updateComment(CommentDto dto) {
        SqlSession session = SqlMapConfig.getSqlSession();
        int res = session.update("comment.updateComment", dto);
        if (res > 0) session.commit();
        session.close();
        return res;
    }

    @Override
    public CommentDto selectCommentById(int commentId) {
        SqlSession session = SqlMapConfig.getSqlSession();
        CommentDto dto = session.selectOne("comment.selectCommentById", commentId);
        session.close();
        return dto;
    }
}
