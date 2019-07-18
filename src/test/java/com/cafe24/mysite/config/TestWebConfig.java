package com.cafe24.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cafe24.config.web.FileUploadConfig;
import com.cafe24.config.web.MessageConfig;
import com.cafe24.config.web.SwaggerConfig;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.cafe24.mysite.controller"})
@Import({TestMVCConfig.class, MessageConfig.class, FileUploadConfig.class, SwaggerConfig.class})
public class TestWebConfig {
	
	
}
