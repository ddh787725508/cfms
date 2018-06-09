package com.ddh.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
 
 
public class UserService {
 
	/**
	 * ʹ��email�˻����е�¼
	 * 
	 * @param email
	 * @param passowrd
	 * @return
	 * @throws UsernameNotFoundException
	 *             �û�������
	 * @throws PasswordException
	 *             �������
	 * @throws StateException
	 *             �˻�������
	 * @throws SQLException
	 *             ���ݿ�����ʧ��
	 */

	public String loginForEmail(String email, String password)
			throws UsernameNotFindException, PasswordException, StateException, SQLException {
		return login(email, password, "SELECT * FROM users where email=?");
	}

	private String login(String key, String password, String sql)
			throws UsernameNotFindException, PasswordException, StateException, SQLException {

		Connection conn = null;
		try {
			conn = DbManger.getConnection();
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, key);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				if (rs.getInt("state") == 0) {
					  
					if (rs.getString("password").equals(password)) {// ѯ�������Ƿ���ͬ
					
						return rs.getString(1);
					} else {
						throw new PasswordException();
					}
				} else {
					throw new StateException();
				}

			} else {
				throw new UsernameNotFindException();
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if(conn!=null) {
				conn.close();
			}
		}

	}
	/**
	 * ʹ���ֻ���������¼
	 * 
	 * @param phone
	 * @param password
	 * @return
	 * @throws UsernameNotFoundException
	 *             �û�������
	 * @throws PasswordException
	 *             �������
	 * @throws StateException
	 *             �˻�������
	 */

	
	
	public String loginForPhone(String phone, String password)
			throws UsernameNotFindException, PasswordException, StateException, SQLException {
		return login(phone, password, "SELECT * FROM users where phonenumber=?");
	}
/**
 * ����Լ��ĺ����б�
 * @param uid
 * @return
 */
	public Vector<UserInfo> getFriendLists(String uid){
		Connection conn=null;
		try {
			conn=DbManger.getConnection();
			PreparedStatement pst = conn.prepareStatement("select u.`uid`,u.`img`,u.`netname`,u.`info` from hy h"
					+ " inner join users u on u.`uid`=h.`hyuid` AND h.`uid`=?");
			pst.setString(1, uid);
		    ResultSet rs=pst.executeQuery();
		    Vector<UserInfo> vector =new Vector<>();
		    while(rs.next()) {
		    	UserInfo userInfo = new UserInfo();
				userInfo.setUid(rs.getString(1));
				userInfo.setImg(rs.getString(2));
				userInfo.setNetname(rs.getString(3));
				userInfo.setInfo(rs.getString(4));
				vector.add(userInfo);
		    }
		    return vector;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}

	/**
	 * �������ϲ�ѯ �������ϲ�ѯ
	 * 
	 * @param uid
	 * @return ������Ϣ
	 * @throws SQLException
	 */
	public UserInfo2 getUserinfo(String uid) throws SQLException {
		Connection conn = null;
		try {
			conn = DbManger.getConnection();
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM USERS WHERE UID=?");
			pst.setString(1, uid);
			ResultSet rs = pst.executeQuery();
			UserInfo2 userInfo2 = new UserInfo2();
			if (rs.next()) {
				userInfo2.setUid(rs.getString("uid"));
				userInfo2.setPhonenumber(rs.getString("phonenumber"));
				userInfo2.setEmail(rs.getString("email"));
				userInfo2.setNetname(rs.getString("netname"));
				userInfo2.setInfo(rs.getString("info"));
				userInfo2.setName(rs.getString("name"));
				userInfo2.setImg(rs.getString("img"));
				userInfo2.setBack(rs.getString("back"));
				userInfo2.setSex(rs.getString("sex"));
				userInfo2.setYy(rs.getInt("yy"));
				userInfo2.setMm(rs.getInt("mm"));
				userInfo2.setDd(rs.getInt("dd"));
			}
			return userInfo2;

		} catch (SQLException e) {
			throw e;
		} finally {
			conn.close();
		}

	}
	/**
	 * ע��
	 * @param username
	 * @param password
	 * @throws UsernameException
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public void regUser(String username, String password) throws UsernameException, SQLException {
		Connection conn = null;
		try {
			conn = DbManger.getConnection();
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM USERS WHERE phonenumber=? or email=?");
			pst.setString(1, username);
			pst.setString(2, username);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				throw new UsernameException();
			}
			if (username.indexOf("@") >= 0) {
				pst = conn.prepareStatement("INSERT INTO users(uid,email,password,createtime) VALUES(?,?,?,SYSDATE())");
			} else if (username.trim().length() == 11) {
				pst = conn.prepareStatement(
						"INSERT INTO users(uid,phonenumber,password,createtime) VALUES(?,?,?,SYSDATE())");
			}
			pst.setString(1, System.currentTimeMillis() + "R" + (int) (Math.random() * 10000));
			pst.setString(2, username);
			pst.setString(3, password);
			if (pst.executeUpdate() <= 0) {
				throw new SQLException();
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			conn.close();
		}

	}

//	public static void main(String[] args) {
//		try {
//			new UserService().loginForPhone("123456789", "123456");
//			System.out.println("�ɹ�");
//		} catch (UsernameNotFindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (PasswordException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (StateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}