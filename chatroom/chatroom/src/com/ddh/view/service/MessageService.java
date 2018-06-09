package com.ddh.view.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 接受服务器的中转消息
 * @author ddh
 *
 */
public class MessageService extends Thread {

	public void run() {
		while (true) {
			try {
				byte[] bytes = new byte[1024 * 32];
				DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
				client.receive(datagramPacket);
            MessagePool.getInstance().addMessage(new String(datagramPacket.getData(), 0, datagramPacket.getData().length));
				System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getData().length));

			} catch (Exception e) {
			}
		}

	}

	private DatagramSocket client = null;

	public MessageService(DatagramSocket client) {
		this.client = client;
		this.start();
	}
}
