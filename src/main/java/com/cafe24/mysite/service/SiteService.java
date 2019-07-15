package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.repository.SiteDao;
import com.cafe24.mysite.vo.SiteVo;

@Service
public class SiteService {

	@Autowired
	SiteDao siteDao;

	public SiteVo getInfo() {
		return siteDao.getInfo();
	}

	public Boolean updateInfo(SiteVo siteVo) {
		return siteDao.updateInfo(siteVo);
	}
}
