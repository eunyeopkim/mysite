package com.douzone.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String list(Model model,
			@RequestParam( value="p", required=true, defaultValue="1") Integer page,
			@RequestParam( value="kwd", required=true, defaultValue="") String keyword) {
		
		Map<String, Object> map = boardService.getContentsList( page, keyword );
		model.addAttribute("map", map);
		return "board/list";
	}
	
	@RequestMapping(value ="/write", method=RequestMethod.GET)
	public String write(HttpSession session) {
		return "board/write";
		
	}
	
	@RequestMapping(value ="/write", method=RequestMethod.POST)
	public String write(HttpSession session,
			@ModelAttribute BoardVo boardVo,
			@RequestParam( value="p", required=true, defaultValue="1") Integer page,
			@RequestParam( value="kwd", required=true, defaultValue="") String keyword) {
		/////////////////////////////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
		return "redirect:/";
		}
		/////////////////////////////////////////////////////////////
		boardVo.setUserNo( authUser.getNo() );
		if( boardVo.getgNo() != null  ) {
			boardService.orderNoUpdate(boardVo);
		}
		boardService.writeContents(boardVo);
		return "redirect:/board";
	}
	
	@RequestMapping("/view/{no}")
	public String view(Model model, @PathVariable("no") Long no) {
		BoardVo boardVo = boardService.getContents(no);
		model.addAttribute("boardVo", boardVo);
		return "board/view";
		
	}
	
	@RequestMapping("/reply/{no}")
	public String reply( Model model, @PathVariable("no") Long no) {
		BoardVo boardVo = boardService.getContents(no);
		boardVo.setoNo(boardVo.getoNo() +1 );
		boardVo.setDepth(boardVo.getDepth() +1 );
		model.addAttribute("boardVo", boardVo);
		return "board/reply";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(HttpSession session,
					@PathVariable("no") Long boardNo) {
		/////////////////////////////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
		return "redirect:/";
		}
		/////////////////////////////////////////////////////////////
		boardService.deleteContents(boardNo,authUser.getNo());
		return "redirect:/board";
	}
	
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.GET )
	public String modify(HttpSession session,
				@PathVariable("no") Long boardNo,
				Model model) {
		/////////////////////////////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
		return "redirect:/";
		}
		/////////////////////////////////////////////////////////////
		BoardVo boardVo = boardService.getContents(boardNo,authUser.getNo());
		model.addAttribute("boardVo", boardVo);
		return "board/modify";
	}
	
	@RequestMapping(value = "/modify/{no}", method = RequestMethod.POST )
	public String modify(HttpSession session,
				@ModelAttribute BoardVo boardVo) {
		/////////////////////////////////////////////////////////////
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		/////////////////////////////////////////////////////////////
		boardVo.setUserNo(authUser.getNo());
		boardService.modifyContents(boardVo);
		return "redirect:/board/view/" + boardVo.getNo();
	}
	
	
	
}
