package com.cafe24.mysite.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cafe24.mysite.config.AppConfig;
import com.cafe24.mysite.config.TestWebConfig;
import com.cafe24.mysite.repository.GuestbookDao;
import com.cafe24.mysite.vo.GuestbookVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class GuestbookServiceTest {
	
	@Autowired
	private GuestbookService2 guestbookService;

	
	@Test
	public void testGuestbookServiceDI() {
		assertNotNull(guestbookService);
	}

	public void testGetContentsList() {
		
		List<GuestbookVo> list = guestbookService.getContentsList(1);
	}

	public void testWriteContent() {
		GuestbookVo vo = new GuestbookVo();
		vo.setName("user1");
		vo.setPassword("1234");
		vo.setContents("test1");
		guestbookService.writeContent(vo);
	}

	@AfterClass
	public void cleanUp() {
//		guestbookDao.deleteAll();
	}
}
