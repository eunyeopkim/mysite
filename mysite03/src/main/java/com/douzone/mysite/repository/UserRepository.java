package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo vo) {
		return sqlSession.insert("user.insert", vo);
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword", vo);
	}
	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		return sqlSession.selectOne("user.findByEmailAndPassword2", map);
	}
	
	public UserVo findByNo(long no) {
		return sqlSession.selectOne("user.findByNo", no);
	}

	public int update(UserVo userVo) {
		return sqlSession.update("user.update", userVo);
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			conn = dataSource.getConnection();
//			if("".equals(vo.getPassword())) {
//				String sql = "update user set name=? ,gender=? where no = ?";
//				pstmt = conn.prepareStatement(sql);
//
//				pstmt.setString(1, vo.getName());
//				pstmt.setString(2, vo.getGender());
//				pstmt.setLong(3, vo.getNo());
//				
//				int count = pstmt.executeUpdate();
//				result = count == 1;
//			} else {
//				String sql = "update user set name=?, password= ?, gender=? where no = ?";
//				pstmt = conn.prepareStatement(sql);
//
//				pstmt.setString(1, vo.getName());
//				pstmt.setString(2, vo.getPassword());
//				pstmt.setString(3, vo.getGender());
//				pstmt.setLong(4, vo.getNo());
//				
//				count = pstmt.executeUpdate();
//
//			}
//			
//
//		} catch (SQLException e) {
//			throw new UserRepositoryException(e.getMessage());
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}

		

		
	}
	

}
