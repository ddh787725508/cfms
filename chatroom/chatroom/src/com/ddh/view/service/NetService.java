package com.ddh.view.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ddh.view.util.Config;

import net.sf.json.JSONObject;

/**
 * 通讯服务 与服务器保持连接状态
 * 1、更新好友在线状态，5秒更新一次
 * 2、登录用户
 * 3、推出账户
 * @author ddh
 *
 */

public class NetService implements Runnable {
	private NetService() {};
	 private static class InnserClass{
	     private static final NetService intance=new NetService();
	    }
   public static final NetService getInstance() {
   	return  InnserClass.intance;
   }
// 这里准备与服务器保存长时间通讯
	public void run() {

		try {

			byte[] bytes = new byte[1024 * 10];
			int len = 0;
			// 好友在线的时时更新
			while (run) {
				output.write("U0002".getBytes());
				output.flush();
				input.read();
				output.write(Config.haoyou_liebiao_data.getBytes());
				output.flush();
				len = input.read(bytes);
				String online = new String(bytes, 0, len);
				System.out.println("在线账户:" + online);
				try {
					if (!Config.haoyou_online.equals(online)) {
						Config.haoyou_online = online;
						Config.haoyouListJPanel.haoyouOnline();
					}
				} catch (Exception e) {
				}
				Config.haoyou_online = online;

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
			}

		} catch (Exception e) {
			run = false;
		}

	}

	private Socket socket = null;
	private InputStream input = null;
	private OutputStream output = null;
	private Thread thread = null;
	private boolean run = false;

	public JSONObject login() throws UnknownHostException, IOException {
		socket = new Socket(Config.IP, Config.LOGIN_PORT);
		input = socket.getInputStream();
		output = socket.getOutputStream();
		String json_str = "{\"username\":\"" + Config.username + "\",\"password\":\"" + Config.password + "\"}";

		// 开始与服务传递消息
		output.write(json_str.getBytes());
		output.flush();

		// 等待服务器回执消息
		byte[] bytes = new byte[1024];
		int len = input.read(bytes);

		json_str = new String(bytes, 0, len);
		JSONObject json = JSONObject.fromObject(json_str);

		// 如果是0 就是登录成功!
		if (json.getInt("state") == 0) {
			// 开启持续的网络连接服务

			if (thread != null) {
				// 询问线程是否还活着
				if (thread.getState() == Thread.State.RUNNABLE) {
					run = false;// 终止线程运行
					try {
						thread.stop();
					} catch (Exception e) {
					}
				}
			}

			//////////////////////////////////////////// 好友信息获得
			output.write(new String("U0001".getBytes(),"utf-8").getBytes());
			output.flush();
			bytes = new byte[1024 * 10];
			len = input.read(bytes);
			String jsonstr = new String(bytes, 0, len);
			////////////////////////////////////////////
			// 解析好友列表
			Config.jiexi_haoyou_json_data(jsonstr);
			System.out.println("好友资料:" + Config.haoyou_json_data);

			//////////////////////////////////////////// 个人资料获得
			output.write(new String("U0003".getBytes(),"utf-8").getBytes());
			output.flush();
			len = input.read(bytes);
			jsonstr=new String(bytes, 0, len);
			Config.geren_json_data = jsonstr.trim();
			System.out.println("个人资料:" + JSONObject.fromObject(Config.geren_json_data));

			/////////////////////////////////////////////启动UDP服务器
			Config.datagramSocket_client=new DatagramSocket();
			//启动心跳包
			new MessageRegService(Config.datagramSocket_client);
			//启动消息服务
			new MessageService(Config.datagramSocket_client); 
			
			/////////////////////////////////////////////
			// 重新开线程与服务器保持通讯
			thread = new Thread(this);
			run = true;
			thread.start();

		}

		return json;
	}


}
