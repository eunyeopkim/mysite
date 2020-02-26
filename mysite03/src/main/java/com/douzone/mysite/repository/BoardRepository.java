package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

	public BoardVo findByNoAndUserNo(Long boardNo, long userNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("boardNo", boardNo);
		map.put("userNo", userNo);
		return sqlSession.selectOne("board.findByNoAndUserNo", map);
	}
	public int orderNoUpdate(long gNo, long oNo) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("gNo",gNo);
		map.put("oNo",oNo);
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

	public List<BoardVo> findAllByPageAndKeword(String keyword, Integer page, Integer size) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("startIndex", (page-1)*size);
		map.put("size", size);
		return sqlSession.selectList("board.findAllByPageAndKeword", map);
		
	}	
	public int updateHit(Long no) {
		int count = sqlSession.update("board.updateHit", no);
		return count;
		
	}
	public Boolean viewUpdate(long no) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection();
			String sql = "update board set hit = hit +1 where no = ?";
			pstmt = conn.prepareStatement(sql);


			pstmt.setLong(1, no);

			count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}
	
	public Boolean deleteUpdate(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection(); 
			String sql = "update board set title ='' where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());

			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://192.168.1.108:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		}
		return conn;
	}



	





	




}
