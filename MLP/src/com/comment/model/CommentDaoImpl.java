package com.comment.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

 // ★ 추가 : 특정 댓글의 대댓글(자식댓글) 목록 조회
    @Override
    public List<CommentDto> selectReplies(int parentId) {
        SqlSession session = SqlMapConfig.getSqlSession();
        List<CommentDto> list = session.selectList("comment.selectReplies", parentId);
        session.close();
        return list;
    }

    // ★ (선택) 추가 : feedId + parentId로 댓글 리스트 조회 (최상위/대댓글 구분용)
    @Override
    public List<CommentDto> selectCommentsByParent(int feedId, Integer parentId) {
        SqlSession session = SqlMapConfig.getSqlSession();
        Map<String, Object> params = new HashMap<>();
        params.put("feedId", feedId);
        params.put("parentId", parentId);
        List<CommentDto> list = session.selectList("comment.selectCommentsByParent", params);
        session.close();
        return list;
    }
}
