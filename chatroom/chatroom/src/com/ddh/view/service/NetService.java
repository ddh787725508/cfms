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
 * ͨѶ���� ���������������״̬
 * 1�����º�������״̬��5�����һ��
 * 2����¼�û�
 * 3���Ƴ��˻�
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
// ����׼������������泤ʱ��ͨѶ
	public void run() {

		try {

			byte[] bytes = new byte[1024 * 10];
			int len = 0;
			// �������ߵ�ʱʱ����
			while (run) {
				output.write("U0002".getBytes());
				output.flush();
				input.read();
				output.write(Config.haoyou_liebiao_data.getBytes());
				output.flush();
				len = input.read(bytes);
				String online = new String(bytes, 0, len);
				System.out.println("�����˻�:" + online);
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

		// ��ʼ����񴫵���Ϣ
		output.write(json_str.getBytes());
		output.flush();

		// �ȴ���������ִ��Ϣ
		byte[] bytes = new byte[1024];
		int len = input.read(bytes);

		json_str = new String(bytes, 0, len);
		JSONObject json = JSONObject.fromObject(json_str);

		// �����0 ���ǵ�¼�ɹ�!
		if (json.getInt("state") == 0) {
			// �����������������ӷ���

			if (thread != null) {
				// ѯ���߳��Ƿ񻹻���
				if (thread.getState() == Thread.State.RUNNABLE) {
					run = false;// ��ֹ�߳�����
					try {
						thread.stop();
					} catch (Exception e) {
					}
				}
			}

			//////////////////////////////////////////// ������Ϣ���
			output.write(new String("U0001".getBytes(),"utf-8").getBytes());
			output.flush();
			bytes = new byte[1024 * 10];
			len = input.read(bytes);
			String jsonstr = new String(bytes, 0, len);
			////////////////////////////////////////////
			// ���������б�
			Config.jiexi_haoyou_json_data(jsonstr);
			System.out.println("��������:" + Config.haoyou_json_data);

			//////////////////////////////////////////// �������ϻ��
			output.write(new String("U0003".getBytes(),"utf-8").getBytes());
			output.flush();
			len = input.read(bytes);
			jsonstr=new String(bytes, 0, len);
			Config.geren_json_data = jsonstr.trim();
			System.out.println("��������:" + JSONObject.fromObject(Config.geren_json_data));

			/////////////////////////////////////////////����UDP������
			Config.datagramSocket_client=new DatagramSocket();
			//����������
			new MessageRegService(Config.datagramSocket_client);
			//������Ϣ����
			new MessageService(Config.datagramSocket_client); 
			
			/////////////////////////////////////////////
			// ���¿��߳������������ͨѶ
			thread = new Thread(this);
			run = true;
			thread.start();

		}

		return json;
	}


}
