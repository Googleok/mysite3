package com.cafe24.mysite.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.GuestbookService2;
import com.cafe24.mysite.vo.GuestbookVo;

@RestController("guestbookAPIController")
@RequestMapping("/api/guestbook")
public class GuestbookController {

   @Autowired
   private GuestbookService2 guestbookService2;
   
   @RequestMapping(value = "/list/{no}", method = RequestMethod.GET)
   public JSONResult list(@PathVariable(value = "no") int no) {
      List<GuestbookVo> list = guestbookService2.getContentsList(1);
      return JSONResult.success(list);
   } 
   
   @RequestMapping(value = "/add", method = RequestMethod.POST)
   public JSONResult add(@RequestBody GuestbookVo guestbookVo) {
	   GuestbookVo newVo = guestbookService2.addContents(guestbookVo);
	   return JSONResult.success(newVo);
   } 
   
   @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
   public JSONResult add(@RequestBody Map<String, Object> map) {
	   Long no = guestbookService2.deleteContents(((Integer)map.get("no")).longValue() ,(String)map.get("password"));
	   return JSONResult.success(no);
   } 
   
}











