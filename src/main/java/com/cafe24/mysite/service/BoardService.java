package com.cafe24.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;

	public boolean write(BoardVo boardVo, Object object) {
		UserVo authUser = (UserVo) object;
		if(boardVo.getParentsNo() != null) {
			BoardVo parentsVo = boardDao.getOne(boardVo.getParentsNo());
			boardVo.setGroupNo(parentsVo.getGroupNo());
			boardVo.setOrderNo(parentsVo.getOrderNo()+1);
			boardVo.setDepth(parentsVo.getDepth()+1);
			boolean updateResult = boardDao.updateOrderNo(boardVo.getOrderNo());
			
		}
		boardVo.setUserNo(authUser.getNo());
		return boardDao.write(boardVo);
	}
	
	

	public List<BoardVo> getList(Integer page) {
		return boardDao.getList(null, page);
	}

	public BoardVo getOne(Long no) {
		boardDao.upHit(no);
		return boardDao.getOne(no);
	}

	public Boolean delete(Long no) {
		return boardDao.delete(no);
	}

	public Boolean modify(BoardVo boardVo) {
		return boardDao.update(boardVo);
	}

	public List<BoardVo> search(String kwd, Integer page) {
		return boardDao.getList(kwd, page);
	}



	public int getListCount() {
		return boardDao.getListCount();
	}

	public int getListSearchCount() {
		return boardDao.getListSearchCount();
	}


}
