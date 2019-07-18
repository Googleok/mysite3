package com.cafe24.mysite.service;

import java.security.spec.PSSParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.vo.UserVo;

@Service
public class UserService{

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Boolean existEmail(String email) {
		UserVo userVo = userDao.get(email);
		return userVo != null ;
	}
	
	public Boolean join(UserVo userVo) {
		userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
		return userDao.insert(userVo);
	}

	public UserVo getUser(Long no) {
		return userDao.get(no);
	}
	public UserVo getUser(UserVo userVo) {
		return userDao.get(userVo);
	}

	public Boolean update(UserVo userVo) {
		return userDao.update(userVo);
	}

	
}
