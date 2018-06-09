package com.ddh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import com.ddh.view.util.Config;

import net.sf.json.JSONObject;

/**
 * 好友信息列表
 * 
 * @author ddh
 *
 */
public class HaoyouliebiaoDialog extends JDialog {
	final JLabel myNetname = new JLabel();
	final JLabel myface = new JLabel(new ImageIcon("face0/15.png"));
	final JLabel myinfo = new JLabel();

	public void gengxin() {

		 System.out.println("genxin="+"进入更新");
	    
	    String gerenjsondata= Config.geren_json_data;
	    System.out.println(gerenjsondata);
		  JSONObject jsonObject = JSONObject.fromObject(gerenjsondata);
          System.out.println(jsonObject);
		   this.setTitle(jsonObject.getString("netname")+" 海哥即时通讯软件");	
		myNetname.setText(jsonObject.getString("netname"));
		myinfo.setText(jsonObject.getString("info"));
		myface.setIcon(new ImageIcon("face0/" + jsonObject.getString("img") + ".png"));

	}
    
	public HaoyouliebiaoDialog() {
		super();
		setBounds(100, 100, 246, 743);

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(5, 5));
		getContentPane().add(panel, BorderLayout.NORTH);

		myface.setPreferredSize(new Dimension(55, 55));

		panel.add(myface, BorderLayout.WEST);

		final JPanel panel_1 = new JPanel();
		final BorderLayout borderLayout = new BorderLayout(5, 5);
		panel_1.setLayout(borderLayout);
		panel.add(panel_1, BorderLayout.CENTER);

		myNetname.setFont(new Font("", Font.BOLD, 16));
		myNetname.setText("小唐唐想静静");
		panel_1.add(myNetname, BorderLayout.CENTER);

		myinfo.setFont(new Font("宋体", Font.PLAIN, 12));
		myinfo.setText("知识的价值不在于占有，而在于使用。");
		panel_1.add(myinfo, BorderLayout.SOUTH);

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		getContentPane().add(panel_2, BorderLayout.SOUTH);

		final JPanel panel_3 = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_3.setLayout(flowLayout_1);
		panel_2.add(panel_3);

		final JButton button = new JButton();
		button.setText("设置");
		panel_3.add(button);

		final JButton button_2 = new JButton();
		button_2.setText("查找");
		panel_3.add(button_2);

		final JPanel panel_4 = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		panel_4.setLayout(flowLayout);
		panel_2.add(panel_4, BorderLayout.EAST);

		final JButton button_1 = new JButton();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				
				System.exit(0);
			}
		});
		button_1.setText("退出");
		panel_4.add(button_1);

		final JTabbedPane tabbedPane = new JTabbedPane();
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		final JPanel panel_5 = new JPanel();
		panel_5.setLayout(new BorderLayout());
		tabbedPane.addTab("我的好友", null, panel_5, null);
		final JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(new HaoyouListJPanel());
		setLocation(com.ddh.util.WindowXY.getXY(HaoyouliebiaoDialog.this.getSize()));
		gengxin();
		//
	}
}
