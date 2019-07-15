package com.cafe24.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean write(BoardVo boardVo) {
		System.out.println(boardVo);
		int count = sqlSession.insert("board.insert", boardVo);
		return count == 1;
	}

	public List<BoardVo> getList(String kwd, Integer page) {
		List<BoardVo> result = null;
		Map<String, Object> map = new HashMap<String, Object>();
		if(kwd != null) {
			map.put("kwd", kwd);
			map.put("page", page);
			return result = sqlSession.selectList("board.getList", map);
		}
		map.put("page", (page-1)*10);
		result = sqlSession.selectList("board.getList", map);
		return result;
	}

	public BoardVo getOne(Long no) {
		return sqlSession.selectOne("board.getOne", no);
	}

	public void upHit(Long no) {
		sqlSession.update("board.upHit", no);
	}

	public Boolean delete(Long no) {
		int count = sqlSession.update("board.deleteUpdate", no);
		return count == 1;
	}

	public Boolean update(BoardVo boardVo) {
		int count = sqlSession.update("board.update", boardVo);
		return count == 1;
	}

	public boolean updateOrderNo(int orderNo) {
		int count = sqlSession.update("board.updateOrderNo", orderNo);
		return count == 1;
	}

	public int getListCount() {
		return sqlSession.selectOne("board.getListCount");
	}

	public int getListSearchCount() {
		return sqlSession.selectOne("board.getListSearchCount");
	}

}
