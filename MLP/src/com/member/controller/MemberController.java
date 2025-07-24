package com.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.dao.MemberDao;
import com.member.dto.MemberDto;


@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//BoardDao bdao = new BoardDao();
	MemberDao mdao = new MemberDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		String command = request.getParameter("command");
		System.out.println("command : "+command);
		
		
		if(command.equals("login")) {
			String id = request.getParameter("id");
		    String pw = request.getParameter("pw");
		    MemberDto dto = mdao.login(id, pw);
		    
		    if(dto != null) {
		    	//System.out.println("mc에서"+dto.getName());
		    	request.getSession().setAttribute("loginUser", dto);
		    	response.sendRedirect("board?command=list");
		        
		    }else {
		        System.out.println("로그인 실패.");
		        response.sendRedirect("index.html");
		    }
		}else if(command.equals("insertform")) {
			response.sendRedirect("insertForm.jsp");
		}else if(command.equals("insert")) {
		    String id = request.getParameter("id");
		    String pw = request.getParameter("pw");
		    String name = request.getParameter("name");
		    String phone = request.getParameter("phone");

		    MemberDto dto = new MemberDto();
		    dto.setId(id);
		    dto.setPw(pw);
		    dto.setName(name);
		    dto.setPhone(phone);
		    dto.setEnabled("Y"); // 회원 기본 상태를 활성으로 설정

		    
		    int res = mdao.insert(dto);

		    if (res > 0) {
		        response.sendRedirect("index.html");
		    } else {
		        response.sendRedirect("insertForm.jsp"); 
		    }
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
