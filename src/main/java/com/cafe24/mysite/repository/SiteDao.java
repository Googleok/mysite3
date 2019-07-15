package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.SiteVo;

@Repository
public class SiteDao {

	@Autowired
	private SqlSession sqlSession;

	public SiteVo getInfo() {
		return sqlSession.selectOne("site.getInfo");
	}

	public Boolean updateInfo(SiteVo siteVo) {
		int count = sqlSession.update("site.updateInfo", siteVo);
		return count == 1;
	}
}
