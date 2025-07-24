package com.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;
import com.comment.dto.CommentDto;
import com.comment.model.CommentDao;
import com.comment.model.CommentDaoImpl;
import com.member.dao.MemberDao;
import com.member.dto.MemberDto;

@WebServlet("/board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    BoardDao bdao = new BoardDao();
    MemberDao mdao = new MemberDao();
    CommentDao cdao = new CommentDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String command = request.getParameter("command");
        System.out.println("command: " + command);

        if (command.equals("list")) {
            String sort = request.getParameter("sort") != null ? request.getParameter("sort") : "desc";
            String search = request.getParameter("search") != null ? request.getParameter("search").trim() : "";

            List<BoardDto> list = bdao.selectAll(sort, search);
            Map<Integer, List<CommentDto>> commentMap = new HashMap<>();

            for (BoardDto dto : list) {
                List<CommentDto> comments = cdao.selectComments(dto.getNo());
                commentMap.put(dto.getNo(), comments);
            }

            MemberDto loginUser = (MemberDto) request.getSession().getAttribute("loginUser");
            String name = loginUser != null ? loginUser.getName() : "Guest";

            request.setAttribute("name", name);
            request.setAttribute("list", list);
            request.setAttribute("commentMap", commentMap);
            request.setAttribute("sort", sort);
            request.setAttribute("search", search);

            RequestDispatcher dis = request.getRequestDispatcher("list.jsp");
            dis.forward(request, response);
        }

        else if (command.equals("mypage")) {
            HttpSession session = request.getSession();
            MemberDto user = (MemberDto) session.getAttribute("loginUser");
            request.setAttribute("dto", user);
            RequestDispatcher dis = request.getRequestDispatcher("mypage.jsp");
            dis.forward(request, response);
        }

        else if (command.equals("insertform")) {
            response.sendRedirect("insertform.jsp");
        }

        else if (command.equals("insert")) {
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            HttpSession session = request.getSession();
            MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
            String name = loginUser != null ? loginUser.getName() : "Guest";

            BoardDto dto = new BoardDto();
            dto.setTitle(title);
            dto.setContent(content);
            dto.setName(name);

            int res = bdao.insert(dto);
            if (res > 0) {
                response.sendRedirect("board?command=list");
            } else {
                response.sendRedirect("insertform.jsp");
            }
        }

        else if (command.equals("detail")) {
            int no = Integer.parseInt(request.getParameter("seq"));
            BoardDto dto = bdao.selectOne(no);
            List<CommentDto> comments = cdao.selectComments(no);

            request.setAttribute("dto", dto);
            request.setAttribute("comments", comments);
            RequestDispatcher dis = request.getRequestDispatcher("detail.jsp");
            dis.forward(request, response);
        }

        else if (command.equals("updateform")) {
            int no = Integer.parseInt(request.getParameter("no"));
            BoardDto dto = bdao.selectOne(no);
            request.setAttribute("dto", dto);
            RequestDispatcher dis = request.getRequestDispatcher("updateform.jsp");
            dis.forward(request, response);
        }

        else if (command.equals("update")) {
            int no = Integer.parseInt(request.getParameter("no"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            BoardDto dto = new BoardDto();
            dto.setNo(no);
            dto.setTitle(title);
            dto.setContent(content);

            int res = bdao.update(dto);
            if (res > 0) {
                response.sendRedirect("board?command=list");
            } else {
                response.sendRedirect("board?command=updateform");
            }
        }

        else if (command.equals("delete")) {
            int no = Integer.parseInt(request.getParameter("no"));
            int res = bdao.delete(no);
            response.sendRedirect("board?command=list");
        }

        else if (command.equals("comment_insert")) {
            int feedId = Integer.parseInt(request.getParameter("feedId"));
            String content = request.getParameter("content");
            HttpSession session = request.getSession();
            MemberDto loginUser = (MemberDto) session.getAttribute("loginUser");
            String commenter = loginUser != null ? loginUser.getName() : "Guest";

            CommentDto cdto = new CommentDto();
            cdto.setFeedId(feedId);
            cdto.setCommenter(commenter);
            cdto.setContent(content);
            cdao.insertComment(cdto);
            response.sendRedirect("board?command=detail&seq=" + feedId);
        }

        else if (command.equals("comment_delete")) {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            int feedId = Integer.parseInt(request.getParameter("feedId"));
            cdao.deleteComment(commentId);
            response.sendRedirect("board?command=detail&seq=" + feedId);
        }

        else if (command.equals("comment_updateform")) {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            CommentDto cdto = cdao.selectCommentById(commentId);
            request.setAttribute("cdto", cdto);
            RequestDispatcher dis = request.getRequestDispatcher("comment_updateform.jsp");
            dis.forward(request, response);
        }

        else if (command.equals("comment_update")) {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            int feedId = Integer.parseInt(request.getParameter("feedId"));
            String content = request.getParameter("content");

            CommentDto cdto = new CommentDto();
            cdto.setCommentId(commentId);
            cdto.setContent(content);
            cdao.updateComment(cdto);
            response.sendRedirect("board?command=detail&seq=" + feedId);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
