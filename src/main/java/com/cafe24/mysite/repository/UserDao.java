package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get(Long no) throws UserDaoException{
		return sqlSession.selectOne("user.getByNo", no);
	}

	public UserVo get(UserVo userVo){
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", userVo.getEmail());
		map.put("password", userVo.getPassword());
		UserVo userVo2 = sqlSession.selectOne("user.getByEmailAndPassword", map);
		return userVo2;
	}	
	
	public Boolean insert(UserVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("user.insert", vo);
		System.out.println(vo);
		
		return 1 == count;
	}	
	
	
	public Boolean update(UserVo userVo) {
		int count = sqlSession.update("user.update", userVo);
		return 1==count;
	}

	public Object getUpdtaeInfo(Long no) {
		return null;
	}

	public UserVo get(String email) {
		UserVo userVo = sqlSession.selectOne("user.getByEmail", email);
		return userVo;
	}

}
