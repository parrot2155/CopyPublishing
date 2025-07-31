package com.comment.model;

import java.util.List;
import com.comment.dto.CommentDto;

public interface CommentDao {
    public int insertComment(CommentDto dto);              // 댓글/대댓글 등록
    public List<CommentDto> selectComments(int feedId);    // 피드의 최상위 댓글(=parentId가 null)
    public int deleteComment(int commentId);               // 댓글 삭제
    public int updateComment(CommentDto dto);              // 댓글 수정
    public CommentDto selectCommentById(int commentId);    // 댓글 하나 조회

    // 추가
    public List<CommentDto> selectReplies(int parentId);   // ★ 대댓글 목록 (parentId로 조회)

    // (선택) feedId와 parentId를 모두 조건으로
    public List<CommentDto> selectCommentsByParent(int feedId, Integer parentId);
}
