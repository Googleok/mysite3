package com.cafe24.mysite.controller.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.cafe24.mysite.vo.UserVo;
import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, TestWebConfig.class})
@WebAppConfiguration
public class UserControllerTest {

   private MockMvc mockMvc;
   
   @Autowired
   private WebApplicationContext webApplicationContext;
   
   @Before
   public void setup() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
   }
   
   @Test
   public void testUserJoin() throws Exception{
	   UserVo userVo = new UserVo();
	   
	   // 1. Normal User's Join Data
	   userVo.setName("박종억");
	   userVo.setEmail("whddjr2225@naver.com");
	   userVo.setPassword("Whddjr12");
	   userVo.setGender("MALE");
	   
	   ResultActions resultActions = 
	            mockMvc
	            .perform(post("/api/user/join")
	            .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo))); // 없는 url
	            
	   resultActions
       .andExpect(status().isOk())
       .andDo(print());
	   
	   // 2. Invalidation in Name :
	   userVo.setName("박");
	   userVo.setEmail("whddjr2225@naver.com");
	   userVo.setPassword("Whddjr12");
	   userVo.setGender("MALE");
	   
	   resultActions = 
	            mockMvc
	            .perform(post("/api/user/join")
	            .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo))); // 없는 url
	            
	   resultActions
       .andExpect(status().isBadRequest())
       .andDo(print())
       .andExpect(jsonPath("$.result", is("fail")));
	   
	   // 3. Invalidation in Password :
	   userVo.setName("박종억");
	   userVo.setEmail("whddjr2225@naver.com");
	   userVo.setPassword("Wh");
	   userVo.setGender("MALE");
	   
	   resultActions = 
	            mockMvc
	            .perform(post("/api/user/join")
	            .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo))); // 없는 url
	            
	   resultActions
       .andExpect(status().isBadRequest())
       .andDo(print())
       .andExpect(jsonPath("$.result", is("fail")));
	   
	   // 4. Invalidation in Gender :
	   userVo.setName("박종억");
	   userVo.setEmail("whddjr2225@naver.com");
	   userVo.setPassword("Whddjr129");
	   userVo.setGender("malef");
	   
	   resultActions = 
	            mockMvc
	            .perform(post("/api/user/join")
	            .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo))); // 없는 url
	            
	   resultActions
       .andExpect(status().isBadRequest())
       .andDo(print())
       .andExpect(jsonPath("$.result", is("fail")));
   }
   
   @Test
   public void testUserLogin() throws Exception{
	   UserVo userVo = new UserVo();
	   
	   // 1. Normal User's Login Data
	   userVo.setEmail("whddjr2225@naver.com");
	   userVo.setPassword("Whddjr129");
	   
	   ResultActions resultActions = 
	            mockMvc
	            .perform(post("/api/user/login")
	            .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo))); // 없는 url
	            
	   resultActions
       .andExpect(status().isOk())
       .andDo(print());
	   
	   // 2. Invalidation in Email :
	   userVo.setEmail("whddjr222");
	   userVo.setPassword("Whddjr12");
	   
	   resultActions = 
	            mockMvc
	            .perform(post("/api/user/login")
	            .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo))); // 없는 url
	            
	   resultActions
       .andExpect(status().isBadRequest())
       .andDo(print())
       .andExpect(jsonPath("$.result", is("fail")));
	   
	   // 3. Invalidation in Password :
	   userVo.setEmail("whddjr2225@naver.com");
	   userVo.setPassword("Whddjr");
	   
	   resultActions = 
	            mockMvc
	            .perform(post("/api/user/login")
	            .contentType(MediaType.APPLICATION_JSON).content(new Gson().toJson(userVo))); // 없는 url
	            
	   resultActions
       .andExpect(status().isBadRequest())
       .andDo(print())
       .andExpect(jsonPath("$.result", is("fail")));
	   
   }
   
}















