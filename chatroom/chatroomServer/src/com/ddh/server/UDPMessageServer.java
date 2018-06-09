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
				// ����������
				if (json.getString("type").equals("reg")) {
					String MyUID = json.getString("myUID");

					// �������µ�IP�Ͷ˿ں�
					UserOnlineList.getInstance().updateOnlineUDP(MyUID, packet.getAddress().getHostAddress(),
							packet.getPort());
					System.out.println("��ע����Ϣ����:"+ jsonStr);

					// ������Ϣת�� // ������Ϣȷ��
				} else if (json.getString("type").equals("msg") || json.getString("type").equals("qr")) {
					String MyUID = json.getString("myUID");
					String toUID = json.getString("toUID");
				 
					// �������µ�IP�Ͷ˿ں�
					UserOnlineList.getInstance().updateOnlineUDP(MyUID, packet.getAddress().getHostAddress(),
							packet.getPort());

					// ���Ҫ��������Ϣ����
					UserInfo toUserinfo = UserOnlineList.getInstance().getOnlineUserInfo(toUID);
                        System.out.println(toUserinfo);
					// ׼��ת�����ͻ��˵����ݰ�
					DatagramPacket datagramPacket = new DatagramPacket(packet.getData(), packet.getLength(),
							InetAddress.getByName(toUserinfo.getUdpip()), toUserinfo.getUdpport());
					      

					// �������ݰ�
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
		// �����̳߳�
		ExecutorService execute = Executors.newFixedThreadPool(1000);
		while (true) {
			try {
				// �ȴ��ͻ��˵�����
				byte[] b = new byte[1024 * 10];
				DatagramPacket datagramPacket = new DatagramPacket(b, b.length);
				datagramSocket.receive(datagramPacket);
				//////////////////////////////////////////

				// ����һ�����ֺ� ����ץ��һ���̴߳���
				execute.execute(new UDPMessageServer(datagramPacket));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
