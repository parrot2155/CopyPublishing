package com.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.member.dto.MemberDto;

public class MemberDao extends SqlMapConfig{
	public MemberDto login(String id,String pw) {
		SqlSession session = null;
	    MemberDto res = null;
	    MemberDto dto = new MemberDto();
	    dto.setId(id);
	    dto.setPw(pw);
	    try {
	        session = getSqlSessionFactory().openSession(true);
	        res = session.selectOne("com.member.selectOne",dto );
	        //System.out.println("dao에서 이름:" + res.getName());

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return res;
	}
	
	public int insert(MemberDto dto) {
        SqlSession session = null;
        int res = 0;

        try {
            session = getSqlSessionFactory().openSession(true);
            res = session.insert("com.member.insert", dto);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) session.close();
        }

        return res;
    }
	
	
}
