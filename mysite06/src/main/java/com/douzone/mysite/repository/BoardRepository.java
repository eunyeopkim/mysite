package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;

	public int insert(BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);

	}

	public BoardVo view(long num) {
		return sqlSession.selectOne("board.view", num);
	}

	public BoardVo findByNo(long num) {
		return sqlSession.selectOne("board.findByNo", num);
	}

	public int orderNoUpdate(long gNo, long oNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("gNo", gNo);
		map.put("oNo", oNo);
		return sqlSession.update("board.updateOrderNo", map);
	}

	public int delete(Long boardNo, long userNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("boardNo", boardNo);
		map.put("userNo", userNo);

		return sqlSession.delete("board.delete", map);
	}

	public int modify(BoardVo boardVo) {
		return sqlSession.update("board.update", boardVo);
	}

	public int getCount(String kwd) {
		return sqlSession.selectOne("board.getCount", kwd);
	}

	public BoardVo findByNoAndUserNo(Long boardNo, long userNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("boardNo", boardNo);
		map.put("userNo", userNo);
		return sqlSession.selectOne("board.findByNoAndUserNo", map);
	}

	public List<BoardVo> findAllByPageAndKeword(String keyword, Integer page, Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("startIndex", (page - 1) * size);
		map.put("size", size);
		return sqlSession.selectList("board.findAllByPageAndKeword", map);

	}

	public int updateHit(Long no) {
		int count = sqlSession.update("board.updateHit", no);
		return count;

	}

}
