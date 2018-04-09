package com.ddh.test1;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;
 

public class Test3 {
  public static void main(String[] args) {
    ActionListener listener=new TimerPrinter();
   Timer t=new Timer(10000,listener);
   t.start();
   JOptionPane.showMessageDialog(null,"Quit program?");
   System.exit(0);
    
  
  }

 
}
class TimerPrinter implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		 Date now=new Date();
		 System.out.println("At the tone, the time is"+now);
		 Toolkit.getDefaultToolkit().beep();
	}
	
}