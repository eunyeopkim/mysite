package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;

@Repository
public class AdminRepository {
	@Autowired
	private SqlSession sqlSession;

	public int update(SiteVo siteVo) {
		System.out.println(siteVo);
		return sqlSession.update("site.update", siteVo);
	}
	
	public SiteVo siteView() {
		return sqlSession.selectOne("site.siteView");
	}
}
