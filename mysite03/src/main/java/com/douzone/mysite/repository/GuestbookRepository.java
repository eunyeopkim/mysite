package com.douzone.mysite.repository;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = sqlSession.selectList("guestbook.findAll");
		return result;
	}
	public int insert(GuestbookVo vo) {
		return sqlSession.insert("guestbook.insert", vo);
	}
	public int delete(GuestbookVo vo) {
		return sqlSession.delete("guestbook.delete", vo);

	}
	
}
