package com.ddh.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ddh.db.UserService;
import com.ddh.db.UsernameException;

import net.sf.json.JSONObject;

public class RegServer implements Runnable {
   private Socket socket=null;
   public   RegServer(Socket socket) {
	 
   this.socket=socket;
   }
   private static HashMap<String, String> hashMap=new HashMap<>();
   
	@Override
	public void run() {
		InputStream input=null;
		OutputStream output=null;
		try {
			input = socket.getInputStream();
			output = socket.getOutputStream();
        //等待客户端发送信息过来
			byte[] bytes=new byte[1024];
			int len=input.read(bytes);
			String str=new String(bytes, 0, len);
			JSONObject json=JSONObject.fromObject(str.trim());
			System.out.println("json="+json.toString());
			String type=json.getString("type");
			System.out.println("json"+type);
			if(type.equals("code")) {
				String username=json.getString("username");
				Random randon=new Random();
				StringBuffer code=new StringBuffer();
				for(int i=0;i<6;i++) {
					code.append(randon.nextInt(10));
				}
				if(username.trim().length()==11) {
					try {
						Long.parseLong(username);
						hashMap.put(username, code.toString());
						SendCode.send(username, code.toString());
						 output.write(new String("{\"state\":0,\"msg\":\"verification code transmission success!\"}".getBytes(),"utf-8").getBytes());
				 
						output.flush();
					   output.flush();
					}catch(Exception e){
						 output.write(new String("{\"state\":1,\"msg\":\"verification code transmission fail!\"}".getBytes(),"utf-8").getBytes());
					 
					}
				}else {
					if (username.indexOf("@") >= 0) {
					   hashMap.put(username, code.toString());
						SendCode.sendEmail(username, code.toString());
						 output.write(new String("{\"state\":0,\"msg\":\"verification code transmission success!\"}".getBytes(),"utf-8").getBytes());

						 
						output.flush();
					} else {
						 output.write(new String("{\"state\":1,\"msg\":\"verification code transmission fail\"}".getBytes(),"utf-8").getBytes());

					 
						output.flush();
					}
				}
			}else if(type.equals("reg")){
				System.out.println("1289y8732465365786387dfugyxvguyfvgfdy");
				String username = json.getString("username");
				String password = json.getString("password");
				System.out.println("username="+username+","+"password="+password);
				String code = json.getString("code");
				System.out.println("code="+code);
				String code1=hashMap.get(username);
				System.out.println("code1="+code1);
				if(code1!=null) {
					hashMap.remove(username);
				}
				System.out.println("code1.equals(code)="+code1.equals(code));
                if(code1.equals(code)){	
                	try {
						new UserService().regUser(username, password);
						System.out.println("注册用户");
					} catch (UsernameException e) {
						 output.write(new String("{\"state\":1,\"msg\":\"The username has already existed!\"}".getBytes(),"utf-8").getBytes());	
						output.flush();
						return;
					} catch (SQLException e) {
						 output.write(new String("{\"state\":3,\"msg\":\"unknow error!\"}".getBytes(),"utf-8").getBytes());
						  output.flush();
						return;
					}
                	output.write(new String("{\"state\":0,\"msg\":\"register success ,you can logining!\"}".getBytes(),"utf-8").getBytes());
                	 
                	 System.out.println("******************************到了向客户端发送消息");
					output.flush();
					System.out.println("到了向客户端发送消息");
				} else {
					
					output.write(new String("{\"state\":2,\"msg\":\"Verification code error, please re-enter!\"}".getBytes(),"utf-8").getBytes());
					output.flush();
				}

			 
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				input.close();
				output.close();
			} catch (Exception e2) {
			}
		}
	}
	public static void openServer() throws IOException {
		ExecutorService service = Executors.newFixedThreadPool(1000);
		ServerSocket server = new ServerSocket(8001);
		while (true) {
			Socket socket = server.accept();
			service.execute(new RegServer(socket));
		}
	}
	 
}
