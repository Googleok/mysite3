package com.cafe24.mysite.controller.api;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cafe24.mysite.config.AppConfig;
import com.cafe24.mysite.config.TestWebConfig;
import com.cafe24.mysite.vo.GuestbookVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class GuestbookControllerTest {

   private MockMvc mockMvc;
   
   @Autowired
   private WebApplicationContext webApplicationContext;
   
   @Before
   public void setup() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }
   
   @Test
   public void testFetchGuestbookList() throws Exception {
      ResultActions resultActions = 
            mockMvc
            .perform(get("/api/guestbook/list/{no}", 1L)
            .contentType(MediaType.APPLICATION_JSON)); // 없는 url
            
      resultActions
         .andExpect(status().isOk())
         .andDo(print())
         .andExpect(jsonPath("$.result", is("success") ))
         .andExpect(jsonPath("$.data", hasSize(2)))         
         .andExpect(jsonPath("$.data[0].no", is(1) ))
         .andExpect(jsonPath("$.data[0].name", is("user1") ))
         .andExpect(jsonPath("$.data[0].contents", is("test1") ))
         .andExpect(jsonPath("$.data[1].no", is(2) ))
         .andExpect(jsonPath("$.data[1].name", is("user2") ))
         .andExpect(jsonPath("$.data[1].contents", is("test2") ));
   }

   @Test
   public void testInsertGuestbook() throws Exception {
      GuestbookVo voMock = new GuestbookVo();
      voMock.setName("user1");
      voMock.setContents("test1");
      
      
//      MailSender mailSender = Mockito.mock(MailSendere.class)
//    	 나중에 이런식으로 쓸 수 있음
//      Mockito.when(voMock.getNo2()).thenReturn(10L);
//      Long no = voMock.getNo2();
      
//      Mockito.when(mailSenderMock.sendMail("")).thenReturn(true);
//      isSuccess = mailSenderMock.sendMail("");
      
      ResultActions resultActions = 
            mockMvc
            .perform(post("/api/guestbook/add")
            .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(voMock))); 
      		
      resultActions
      .andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.result", is("success") ))
      .andExpect(jsonPath("$.data.name", is(voMock.getName())))         
      .andExpect(jsonPath("$.data.contents", is(voMock.getContents())));         
      
   }
   
   
   @Test
   public void testDeleteGuestbook() throws Exception{
      
      Map<String, Object> map = new HashMap<String,Object>();
      map.put("no", 3L);
      map.put("password", "1234");
   
      ResultActions resultActions = 
         mockMvc
         .perform(delete("/api/guestbook/delete").contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(map)));

      //delete 요청에 대한 응답이 왔을때 클라이언트 단에서 또 삭제를 해줘야하니깐 No 값을 받아야 함 
      
      resultActions.andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.result", is("success")))
      ;
      
   }
   
}















