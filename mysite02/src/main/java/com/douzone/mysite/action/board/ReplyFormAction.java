package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		System.out.println(request.getParameter("gNo"));
		long no = Long.parseLong(request.getParameter("no"));
		long gNo = Long.parseLong(request.getParameter("gNo"));
		long oNo = Long.parseLong(request.getParameter("oNo"));
		long depth = Long.parseLong(request.getParameter("depth"));
		long userNo = Long.parseLong(request.getParameter("userNo"));
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setgNo(gNo);
		vo.setoNo(oNo);
		vo.setDepth(depth);
		vo.setUserNo(userNo);
		request.setAttribute("vo", vo);
		
		WebUtil.forward("/WEB-INF/views/board/reply.jsp", request, response);

	}

}
