package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.Paging;
import com.douzone.web.action.Action;
import com.douzone.web.util.WebUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		List<BoardVo> list = new BoardRepository().findAll();
//		long blockStartNum;		//블락시작넘버
//		long blockLastNum;		//블락마지막넘버
//		long lastPageNum;		//마지막페이지
//		long curPage;			//현재페이지
//		long limit;				//갯수제한
//		long maxPage;
//		long startPage;
//		long endPage;
//		long count;
//		
//		blockStartNum = Long.parseLong(request.getParameter("page"));
//		blockLastNum = Long.parseLong(request.getParameter("blockLastNum"));
//		String kwd = request.getParameter("kwd");
//		
//		
//		
//		count = new BoardRepository().getCount(kwd);
//		
//		limit = 5;
//		maxPage = (long) ((double) count / limit + 0.9);
//		startPage = (count - 1) * limit + 1;
//		endPage = startPage + limit - 1;
//		
//		
//		Paging pi = new Paging(blockStartNum, blockLastNum, lastPageNum, curPage, limit, maxPage, startPage, endPage, count);
//		List<BoardVo> list2 = new BoardRepository().pageSearchList(kwd,pi);
//		
//		request.setAttribute("kwd", kwd);
		request.setAttribute("list", list);
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);

	}

}
