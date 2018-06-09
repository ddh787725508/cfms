package com.ddh.view.util;

import java.net.DatagramSocket;
import java.util.Hashtable;
import java.util.Vector;

import com.ddh.view.FaceJPanel;
import com.ddh.view.HaoyouListJPanel;
import com.ddh.view.LiaotianFrame;
import com.ddh.view.Msg;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Config {
  public static final String IP="127.0.0.1";
  public static final int LOGIN_PORT=8000;
  public static String username;
  public static String password;
  public static final int REG_PORT = 8001;
  public static String haoyou_json_data = "";
  public static HaoyouListJPanel haoyouListJPanel;
	public static String haoyou_liebiao_data = "";
	/**
	 * 取出好友列表值
	 * 
	 * @param haoyou_json_data
	 */
	public static void jiexi_haoyou_json_data(String haoyou_json_data) {
		Config.haoyou_json_data = haoyou_json_data;
		JSONArray json = JSONArray.fromObject(haoyou_json_data);
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < json.size(); i++) {
			JSONObject jsonobj = (JSONObject) json.get(i);
			stringBuffer.append(jsonobj.getString("uid"));
			stringBuffer.append(",");
		}
		haoyou_liebiao_data = stringBuffer.toString();

         System.out.println("haoyou_json_data="+haoyou_json_data);
	}

	public static String geren_json_data = "";

	public static String haoyou_online = "";
	//UDP发送和接收 以及心跳端
		public static DatagramSocket datagramSocket_client=null;
		// 聊天窗口登记
		public static Hashtable<String, LiaotianFrame> liaotianTable = new Hashtable<String, LiaotianFrame>();

		// 显示聊天窗口
		public static void showLiaotianFrame(String uid, String netName, String info, String img,Vector<Msg> msg) {

			if (liaotianTable.get(uid) == null) {
				LiaotianFrame liaotian = new LiaotianFrame(uid, netName, img, info,msg);
				liaotianTable.put(uid, liaotian);
			} else {
				liaotianTable.get(uid).setAlwaysOnTop(true);
				liaotianTable.get(uid).setVisible(true);
			}

		}

		public static void closeLiaotianFrame(String uid) {

			liaotianTable.remove(uid);
		}
		public static Hashtable<String, FaceJPanel> list = new Hashtable();
}


