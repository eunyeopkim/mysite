package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String message = request.getParameter("content");
		
		GuestbookVo vo = new GuestbookVo();
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		vo.setName(name);
		vo.setPassword(password);
		vo.setContents(message);
		vo.setRegDate(sdf.format(date));
		
		new GuestbookRepository().insert(vo);
		// insert 후 돌아가야하는 URI주소를 redirect해준다.
		WebUtil.redirect(request.getContextPath()+"/guestbook?a=list", request, response);

	}

}
