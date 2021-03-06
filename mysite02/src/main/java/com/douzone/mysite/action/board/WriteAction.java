package com.douzone.mysite.action.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		long userno = Long.parseLong(request.getParameter("userno"));
		
		BoardVo vo = new BoardVo();
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    vo.setTitle(title);
	    vo.setContents(contents);
	    vo.setUserNo(userno);
	    vo.setRegDate(sdf.format(date));
		
		System.out.println(vo);
		request.setAttribute("vo", vo);
		new BoardRepository().insert(vo);
		WebUtil.redirect(request.getContextPath()+"/board?a=list", request, response);

	}

}
