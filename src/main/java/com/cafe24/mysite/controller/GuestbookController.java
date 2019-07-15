package com.cafe24.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	//insert
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.insert(guestbookVo);
		return "redirect:/guestbook";
	}
	
	//deleteform
	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "no")Long no, Model model) {
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}
	
	// delete
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(
			@ModelAttribute GuestbookVo guestbookVo
			) {
		guestbookService.delete(guestbookVo);
		return "redirect:/guestbook";
	}
	
	//getList
	@RequestMapping(value = {"/list", "/", ""}, method = RequestMethod.GET)
	public String getList(Model model) {
		model.addAttribute("list",  guestbookService.getList()); 
		return "/guestbook/list";
	}
}
