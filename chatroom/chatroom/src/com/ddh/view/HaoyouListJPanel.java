package com.ddh.view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ddh.view.util.Config;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HaoyouListJPanel extends JPanel {
	/**
	 * Create the panel
	 */
	public HaoyouListJPanel() {
		super();
		setLayout(null);

		gengxin();
		//

		Config.haoyouListJPanel = this;
	}

	/// �������߸���
	public void haoyouOnline() {
		// �����б�
		String zaixianliebiao = Config.haoyou_online;
System.out.println("zaixianliebiao="+zaixianliebiao);
		String[] uids = zaixianliebiao.split(",");
		Set<String> keys = Config.list.keySet();
		for (String string : keys) {
		Config.list.get(string).setOnline(false);
		}

		if (!zaixianliebiao.trim().equals("notFound") &&!zaixianliebiao.trim().equals("")) {
			for (String uid : uids) {
				if (!uid.trim().equals("") ) { 
					FaceJPanel faceJPanel = (FaceJPanel)Config.list.get(uid);
					faceJPanel.setOnline(true);
				}
			}
		}
		Collection<FaceJPanel> faceJPanels =Config.list.values();
		List<FaceJPanel> list = new ArrayList(faceJPanels);
		Collections.sort(list);

		this.removeAll();
		int i = 0;
		for (FaceJPanel faceJPanel : list) {
			faceJPanel.setBounds(0, i++ * 60, 546, 59);
			this.add(faceJPanel);
		}

		this.setPreferredSize(new Dimension(0, 40 * list.size()));
		this.updateUI();
	}

	

	// �����б�
	public void gengxin() {
		// �����б�
		String haoyouliebiao = Config.haoyou_json_data;
       System.out.println("haoyouliebiao="+haoyouliebiao);
		JSONArray jsonArray = JSONArray.fromObject(haoyouliebiao);

		if (Config.list.size() == 0) {// ��һ�μ����б�

			 
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);

				Config.list.put(jsonObject.getString("uid"), new FaceJPanel(jsonObject.getString("img"),
						jsonObject.getString("netname"), jsonObject.getString("info"), jsonObject.getString("uid")));

			}

		} else {// �Ѿ����б�������

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String uid = jsonObject.getString("uid");

				FaceJPanel faceJPanel = (FaceJPanel)Config. list.get(uid);
				if (faceJPanel != null) {// �Ѿ�����
					faceJPanel.setNetname(jsonObject.getString("netname"));
					faceJPanel.setInfo(jsonObject.getString("info"));
					faceJPanel.setImage(jsonObject.getString("img"));

				} else {// ������
					Config.list.put(jsonObject.getString("uid"),
							new FaceJPanel(jsonObject.getString("img"), jsonObject.getString("netname"),
									jsonObject.getString("info"), jsonObject.getString("uid")));

				}

			}

		}
		haoyouOnline();
	}


}
