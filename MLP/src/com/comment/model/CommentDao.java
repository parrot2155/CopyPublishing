package com.comment.model;

import java.util.List;
import com.comment.dto.CommentDto;

public interface CommentDao {
    public int insertComment(CommentDto dto);              // 댓글 등록
    public List<CommentDto> selectComments(int feedId);    // 댓글 목록
    public int deleteComment(int commentId);               // 댓글 삭제
    public int updateComment(CommentDto dto);              // 댓글 수정
    public CommentDto selectCommentById(int commentId);    // 댓글 하나 조회
}
