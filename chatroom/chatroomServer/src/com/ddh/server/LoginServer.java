package com.ddh.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ddh.db.PasswordException;
import com.ddh.db.StateException;
import com.ddh.db.UserInfo;
import com.ddh.db.UserInfo2;
import com.ddh.db.UserService;
import com.ddh.db.UsernameNotFindException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LoginServer implements Runnable {
  private Socket socket=null;
   public LoginServer(Socket socket) {
	   this.socket=socket;
   }
   public void run() {
	   InputStream in=null;
	   OutputStream out=null;
	   String uid=null;
	    try {
	    	
			in=socket.getInputStream();
			out=socket.getOutputStream();
			//等待客户端的信息
			byte[] bytes=new byte[1024];
			int len=in.read(bytes);
			String  str=new String(bytes,0,len);
			JSONObject json=JSONObject.fromObject(str);
			String username=json.getString("username");
			String password=json.getString("password");
			boolean type=false;
			if(username.trim().length()==11 && username.indexOf("@") <= -1) {
				try {
					// 解析手机号码是不是纯数字
					Long.parseLong(username);
					type=true;
				} catch (NumberFormatException e) {
				;
				 out.write(new String("{\"state\":4,\"msg\":\"unknow error!\"}".getBytes(),"utf-8").getBytes());
				 out.flush();
				}
			}else {
				type=false;
			}
			try {
				

				if (type == true) {// 手机号码
					uid = new UserService().loginForPhone(username, password);
					// 登记登录信息
					UserOnlineList.getInstance().regOnline(uid, socket, null, username);
				} else {// 其他方式登录
					uid = new UserService().loginForEmail(username, password);
					// 登记登录信息
					UserOnlineList.getInstance().regOnline(uid, socket, username, null);
				}

				out.write(new String("{\"state\":0,\"msg\":\"login success!\"}".getBytes(),"utf-8").getBytes());
				out.flush();
				//接受客户端发来的指令要求
				
				while(true) {
					bytes = new byte[2048];
					len = in.read(bytes);
					String command = new String(bytes, 0, len);
					if (command.equals("U0001")) { // 更新好友列表

						Vector<com.ddh.db.UserInfo> userinfos = new UserService().getFriendLists(uid);
						out.write(JSONArray.fromObject(userinfos).toString().getBytes());
						out.flush();

					} else if (command.equals("U0002")) {// 更新好友在线
						out.write(1);
						out.flush();
						// 获得好友的列表编号
						len = in.read(bytes);// 1324564,12346546,123456456,2346546,2456456,1237489,137687
						String str1 = new String(bytes, 0, len);
						String[] ids = str1.split(",");

						StringBuffer stringBuffer = new StringBuffer();
						for (String string : ids) {
							if (UserOnlineList.getInstance().isUserOnline(string)) {
								stringBuffer.append(string);
								stringBuffer.append(",");
							}
						}
						if (stringBuffer.length() == 0) {
							// 没有好友在线
							out.write("notFound".getBytes());
							out.flush();
						} else {
							// 回执好友在线列表
							out.write(stringBuffer.toString().getBytes());
							out.flush();
						}

					} else if (command.equals("U0003")) { // 更新个人资料

						UserInfo2 userinfo2 = new UserService().getUserinfo(uid);
						out.write(JSONObject.fromObject(userinfo2).toString().getBytes());
						out.flush();

					} else if (command.equals("E0001")) {// 修改个人资料
						

					} else if (command.equals("EXIT")) {// 退出用户登录
					 UserOnlineList.getInstance().logout(uid);
						return;
					}
				}
				
			} catch (UsernameNotFindException e) {
				out.write(new String("{\"state\":2,\"msg\":\"incorrect username!\"}".getBytes(),"utf-8").getBytes());
				out.flush();
				return;
			} catch (PasswordException e) {
				out.write(new String("{\"state\":1,\"msg\":\"incorrect password !\"}".getBytes(),"utf-8").getBytes());
				out.flush();
				return;
			} catch (StateException e) {
				out.write(new String("{\"state\":3,\"msg\":\"account lockout,please contact admin!\"}".getBytes(),"utf-8").getBytes());
				out.flush();
				return;
			} catch (SQLException e) {
				out.write(new String("{\"state\":4,\"msg\":\"unknow error!\"}".getBytes(),"utf-8").getBytes());
				out.flush();
				return;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				UserOnlineList.getInstance().logout(uid);
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    
   }
   public static void openServer() throws Exception {
	  ExecutorService service= Executors.newFixedThreadPool(1000);
       ServerSocket server=new ServerSocket(8000);
    
    		while (true) {
    			Socket socket = server.accept();
    			socket.setSoTimeout(10000);
    			service.execute(new LoginServer(socket));
    		}
   }
   public static void main(String[] args) throws Exception {
	   openServer();
   }
   
}
