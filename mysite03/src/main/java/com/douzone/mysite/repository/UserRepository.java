package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	public int insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0 ;
		try {
			conn = getConnection();
			String sql = "insert into user values(null, ?, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new UserRepositoryException(e.getMessage());
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
		return count;

	}

	

	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo userVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select no, name "
					+ " from user"
					+ " where email = ?"
					+ " and password = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				
				userVo = new UserVo();
				
				userVo.setNo(no);
				userVo.setName(name);
			}

		} catch (SQLException e) {
			throw new UserRepositoryException(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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

		return userVo;
	}
	public UserVo findByNo(long no) {
		UserVo userVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select no, name, email, password, gender"
					+ " from user"
					+ " where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				long no1 = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				String gender = rs.getString(5);
				
				userVo = new UserVo();
				
				userVo.setNo(no1);
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setPassword(password);
				userVo.setGender(gender);
			}

		} catch (SQLException e) {
			throw new UserRepositoryException(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
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

		return userVo;

	}

	public Boolean update(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			if("".equals(vo.getPassword())) {
				String sql = "update user set name=? ,gender=? where no = ?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());
				
				int count = pstmt.executeUpdate();
				result = count == 1;
			} else {
				String sql = "update user set name=?, password= ?, gender=? where no = ?";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getGender());
				pstmt.setLong(4, vo.getNo());
				
				int count = pstmt.executeUpdate();
				result = count == 1;
			}
			

		} catch (SQLException e) {
			throw new UserRepositoryException(e.getMessage());
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
			throw new UserRepositoryException("드라이버 로딩 실패: " + e);
		}
		return conn;
	}
}
