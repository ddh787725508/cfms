package com.ddh.server;

public class Start {

	public static void main(String[] args) {
		new Thread() {

			public void run() {
				try {
				    System.out.println("��¼�����������ɹ�");
					LoginServer.openServer();
				  
				      
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();

		new Thread() {

			public void run() {
				try {
					System.out.println("ע������������ɹ�");

					RegServer.openServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
		new Thread() {

			public void run() {
				try {
					System.out.println("��Ϣ��ת�����������ɹ�");

					UDPMessageServer.openServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
}
