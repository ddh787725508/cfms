package com.ddh.view.service;

import java.util.HashMap;
import java.util.LinkedList;

import com.ddh.view.FaceJPanel;
import com.ddh.view.LiaotianFrame;
import com.ddh.view.Msg;
import com.ddh.view.util.Config;

import net.sf.json.JSONObject;

public class MessagePool {
	private MessagePool() {};
	 private static class InnserClass{
	     private static final MessagePool intance=new MessagePool();
	    }
   public static final MessagePool getInstance() {
   	return  InnserClass.intance;
   }
   public static HashMap<String,LinkedList<Msg>> hashMap=new HashMap();
  public void addMessage(String json) {
	  JSONObject jsonObject=JSONObject.fromObject(json);
	  String toUID = jsonObject.getString("toUID");
		String myUID = jsonObject.getString("myUID");
		String msg = jsonObject.getString("msg");
		String type = jsonObject.getString("type");
		String code = jsonObject.getString("code");
		//把接受的消息，封装到Msg中
		Msg msgObject=new Msg();
		msgObject.setCode(code);
		msgObject.setMsg(msg);
		msgObject.setMyUID(myUID);
		msgObject.setToUID(toUID);
		msgObject.setType(type);

		try {
			LiaotianFrame liaotianFrame = Config.liaotianTable.get(myUID);
			if (liaotianFrame.isVisible()) {

				liaotianFrame.addMessage(msgObject);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {

			FaceJPanel faceJPanel= Config.list.get(myUID);
			faceJPanel.addMessage(msgObject);
			

//			// 链表集合存储Msg对象 以便今后读取里面的消息
//			LinkedList<Msg> list = hashMap.get(myUID);
//			if (list == null) {
//				list = new LinkedList();
//			}
//			list.add(msgObj);
//
//			hashMap.put(myUID, list);
		}

  }
}
