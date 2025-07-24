package com.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.board.dto.BoardDto;

public class BoardDao extends SqlMapConfig {
    
    public List<BoardDto> selectAll(String sort, String search) {
        SqlSession session = null;
        List<BoardDto> res = null;
        
        session = getSqlSessionFactory().openSession(true);
        // sort와 search를 MyBatis로 전달
        java.util.Map<String, String> params = new java.util.HashMap<>();
        params.put("sort", sort.equals("asc") ? "ASC" : "DESC");
        params.put("search", "%" + search + "%"); // LIKE 검색용
        
        res = session.selectList("com.board.selectAll", params);
        
        session.close();
        return res;
    }

    public int insert(BoardDto dto) {
        int res = 0;
        SqlSession session = null;
        
        session = getSqlSessionFactory().openSession(true);
        res = session.insert("com.board.insert", dto);
        session.close();
        return res;
    }

    public BoardDto selectOne(int no) {
        SqlSession session = null;
        BoardDto res = null;
        
        session = getSqlSessionFactory().openSession(true);
        res = session.selectOne("com.board.selectOne", no);
        session.close();
        return res;
    }

    public int update(BoardDto dto) {
        int res = 0;
        SqlSession session = null;
        
        session = getSqlSessionFactory().openSession(true);
        res = session.update("com.board.update", dto);
        session.close();
        return res;
    }

    public int delete(int no) {
        SqlSession session = null;
        int res = 0;
        
        session = getSqlSessionFactory().openSession(true);
        res = session.delete("com.board.delete", no);
        session.close();
        return res;
    }
}