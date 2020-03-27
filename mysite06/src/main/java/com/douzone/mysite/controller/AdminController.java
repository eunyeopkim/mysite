package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.AdminService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.security.Auth;

@Auth("ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = new SiteVo();
		siteVo = adminService.siteView();
		System.out.println("view: " + siteVo);
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}
	
	@RequestMapping(value = "/main/update", method=RequestMethod.POST)
	public String update(
		@ModelAttribute SiteVo siteVo,
		@RequestParam(value="file1") MultipartFile multipartFile,
		Model model) {
		String url = adminService.restore(siteVo, multipartFile);
		model.addAttribute("url", url);
		return "redirect:/main";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
