package com.douzone.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String password = request.getParameter("pass");
		String no = request.getParameter("no");
		GuestbookVo vo = new GuestbookVo();	
		vo.setNo(Long.parseLong(no));
		vo.setPassword(password);
		new GuestbookRepository().delete(vo);
		WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);

	}

}
