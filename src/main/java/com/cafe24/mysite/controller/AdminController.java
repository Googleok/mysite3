package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mysite.service.FileuploadService;
import com.cafe24.mysite.service.SiteService;
import com.cafe24.mysite.vo.SiteVo;

//@Auth(role=Auth.Role.ADMIN)

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired	
	private SiteService siteService;
	
	@Autowired
	private FileuploadService fileUploadService;
	
	@RequestMapping({"", "/main"})
	public String main() {
		return "/admin/main";
	}
	
	@RequestMapping(value="/main/update", method = RequestMethod.POST)
	public String main_update(
			@RequestParam("title") String title,
			@RequestParam("welcomeMessage") String welcomeMessage,
			@RequestParam("profile") MultipartFile multipartFile,
			@RequestParam("description") String description
			) {
		String profile = fileUploadService.restore(multipartFile);
		SiteVo vo = new SiteVo(title, welcomeMessage, profile, description, "true");
		siteService.updateInfo(vo);
		return "redirect:/admin/main";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "/admin/user";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "/admin/board";
	}
	
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "/admin/guestbook";
	}
	
	
	
}
