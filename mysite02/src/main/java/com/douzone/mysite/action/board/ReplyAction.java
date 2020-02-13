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

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		long no = Long.parseLong(request.getParameter("no"));
		long gNo = Long.parseLong(request.getParameter("gNo"));
		long oNo = Long.parseLong(request.getParameter("oNo"));
		long depth = Long.parseLong(request.getParameter("depth"));
		long userNo = Long.parseLong(request.getParameter("userNo"));
		
		
		BoardVo vo = new BoardVo();
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    
	    vo.setTitle(title);
	    vo.setContents(contents);
	    vo.setNo(no);
	    vo.setRegDate(sdf.format(date));
	    vo.setgNo(gNo);
	    vo.setoNo(oNo);
	    vo.setDepth(depth);
	    vo.setUserNo(userNo);
		request.setAttribute("vo", vo);
		
		new BoardRepository().replyUpdate(vo);
		new BoardRepository().replyInsert(vo);
		WebUtil.redirect(request.getContextPath()+"/board?a=list&no="+no, request, response);

	}

}
