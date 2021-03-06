package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.Paging;

public class BoardRepository {
	public List<BoardVo> findAll() {
		List<BoardVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select b.no, b.title, b.contents, b.hit, b.reg_date, b.g_no, b.o_no, b.depth, b.user_no, u.name"
					+ " from board b, user u" + " where b.user_no=u.no" + " order by g_no desc, o_no asc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				long hit = rs.getLong(4);
				String regDate = rs.getString(5);
				long gNo = rs.getLong(6);
				long oNo = rs.getLong(7);
				long depth = rs.getLong(8);
				long userNo = rs.getLong(9);
				String userName = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
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

		return result;
	}

	public Boolean insert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into board values(null, ?, ?, 0, ?, (select max(g_no) from board b)+1, 1, 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setString(3, vo.getRegDate());
			pstmt.setLong(4, vo.getUserNo());

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
	
	public Boolean replyInsert(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			//insert into board values (null, '나두', '배고파', 0, now(), 1, 2, 1, 2)
			String sql = "insert into board values" + 
					"(null, ?, ?, 0, now(), ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getgNo());
			pstmt.setLong(4, vo.getoNo()+1);
			pstmt.setLong(5, vo.getDepth()+1);
			pstmt.setLong(6, vo.getUserNo());

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
	public Boolean replyUpdate(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			// update board set o_no = o_no + 1 where o_no >= ?;
			String sql = "update board set o_no = o_no + 1 where g_no = ? and o_no >= ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1,vo.getgNo());
			pstmt.setLong(2,vo.getoNo());

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

	public Boolean update(BoardVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "update board set title=? , contents=? where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

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
	public List<BoardVo> findTitle(BoardVo bvo) {
		List<BoardVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select b.no, b.title, b.contents, b.hit, b.reg_date, b.g_no, b.o_no, b.depth, b.user_no, u.name" + 
					" from board b, user u" + 
					" where b.user_no=u.no and b.title like concat('%',?,'%')" + 
					" order by g_no desc, o_no asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getTitle());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				long hit = rs.getLong(4);
				String regDate = rs.getString(5);
				long gNo = rs.getLong(6);
				long oNo = rs.getLong(7);
				long depth = rs.getLong(8);
				long userNo = rs.getLong(9);
				String userName = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
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

	public BoardVo view(long num) {
		BoardVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select no, title, contents, hit, reg_date, g_no, o_no, depth, user_no from board where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				long hit = rs.getLong(4);
				String regDate = rs.getString(5);
				long gNo = rs.getLong(6);
				long oNo = rs.getLong(7);
				long depth = rs.getLong(8);
				long userNo = rs.getLong(9);

				vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
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

		return vo;
	}

	public BoardVo findByNo(long num) {
		BoardVo boardVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();

			String sql = "select no, title, contents, hit, reg_date" + " from board" + " where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String contents = rs.getString(3);
				long hit = rs.getLong(4);
				String reg_date = rs.getString(5);

				boardVo = new BoardVo();

				boardVo.setNo(no);
				boardVo.setTitle(title);
				boardVo.setContents(contents);
				boardVo.setHit(hit);
				boardVo.setRegDate(reg_date);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
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
		return boardVo;
	}

	public List<BoardVo> pageSearchList(String kwd, Paging pi)  {
		List<BoardVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {

			conn = getConnection();
			if(kwd == null) {
				sql = "select *" + 
						" from (	select  @rownum:=@rownum+1 AS RNUM, a.*, u.name" + 
						" from(  select *" + 
						" from board" + 
						" order by g_no desc, o_no asc ) a, user u ,(select @rownum:=0) c" + 
						" where a.user_no = u.no) z" + 
						" where RNUM >= ? and RNUM <= ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, pi.getBlockStartNum());
				pstmt.setLong(2, pi.getBlockLastNum());
				rs = pstmt.executeQuery();
			}
			else {
				sql = "select *" + 
						" from (	select  @rownum:=@rownum+1 AS RNUM, a.*, u.name" + 
						" from(  select *" + 
						" from board" + 
						" order by g_no desc, o_no asc ) a, user u ,(select @rownum:=0) c" + 
						" where a.user_no = u.no) z" + 
						" where RNUM >= ? and RNUM <= ?"+
						" and z.title like concat('%',?,'%')";

				pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, pi.getBlockStartNum());
				pstmt.setLong(2, pi.getBlockLastNum());
				pstmt.setString(3, kwd);
				rs = pstmt.executeQuery();
			}

			if (rs.next()) {
				long rnum = rs.getLong(1);
				long no = rs.getLong(2);
				String title = rs.getString(3);
				String contents = rs.getString(4);
				long hit = rs.getLong(5);
				String regDate = rs.getString(6);
				long gNo = rs.getLong(7);
				long oNo = rs.getLong(8);
				long depth = rs.getLong(9);
				long userNo = rs.getLong(10);
				String userName = rs.getString(11);

				BoardVo vo = new BoardVo();
				vo.setRnum(rnum);
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setgNo(gNo);
				vo.setoNo(oNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);

				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
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

		return result;
	}

	public long getCount(String kwd) {
		long count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = getConnection();
			String sql = null;
			if(kwd == null) {
				sql = "select count(*) as count from board";
				pstmt = conn.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
			}
			else {
				sql = "select count(*) as count from board where board.title like concat('%',?,'%')";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, kwd);
				rs = pstmt.executeQuery();
			}
			
			if (rs.next()) {
				long cnt = rs.getLong(1);

				count = cnt;
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
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

		return count;
	}
}
