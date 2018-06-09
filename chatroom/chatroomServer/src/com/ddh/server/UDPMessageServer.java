package com.ddh.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSONObject;

public class UDPMessageServer implements Runnable{
  private DatagramPacket packet=null;
	public UDPMessageServer(DatagramPacket packet) {
	  this.packet=packet;
  }
	@Override
		public void run() {
			 
			try {
				String jsonStr = new String(packet.getData(), 0, packet.getLength());
				JSONObject json = JSONObject.fromObject(jsonStr);
                 System.out.println("json="+json);
				// 处理心跳包
				if (json.getString("type").equals("reg")) {
					String MyUID = json.getString("myUID");

					// 更新最新的IP和端口号
					UserOnlineList.getInstance().updateOnlineUDP(MyUID, packet.getAddress().getHostAddress(),
							packet.getPort());
					System.out.println("有注册信息发来:"+ jsonStr);

					// 处理信息转发 // 处理消息确认
				} else if (json.getString("type").equals("msg") || json.getString("type").equals("qr")) {
					String MyUID = json.getString("myUID");
					String toUID = json.getString("toUID");
				 
					// 更新最新的IP和端口号
					UserOnlineList.getInstance().updateOnlineUDP(MyUID, packet.getAddress().getHostAddress(),
							packet.getPort());

					// 获得要接收你信息的人
					UserInfo toUserinfo = UserOnlineList.getInstance().getOnlineUserInfo(toUID);
                        System.out.println(toUserinfo);
					// 准备转发到客户端的数据包
					DatagramPacket datagramPacket = new DatagramPacket(packet.getData(), packet.getLength(),
							InetAddress.getByName(toUserinfo.getUdpip()), toUserinfo.getUdpport());
					      

					// 发出数据包
					datagramSocket.send(datagramPacket);
					System.out.println("");
				}
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	private static DatagramSocket datagramSocket=null;
	public static void openServer() throws Exception {
		datagramSocket = new DatagramSocket(8002);
		// 制作线程池
		ExecutorService execute = Executors.newFixedThreadPool(1000);
		while (true) {
			try {
				// 等待客户端的数据
				byte[] b = new byte[1024 * 10];
				DatagramPacket datagramPacket = new DatagramPacket(b, b.length);
				datagramSocket.receive(datagramPacket);
				//////////////////////////////////////////

				// 数据一旦到手后 立马抓出一个线程处理
				execute.execute(new UDPMessageServer(datagramPacket));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
