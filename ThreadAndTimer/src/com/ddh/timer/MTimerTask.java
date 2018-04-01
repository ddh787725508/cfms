package com.ddh.timer;

import java.util.Timer;
import java.util.TimerTask;
 

public class MTimerTask extends TimerTask{
      static int count=0;
	@Override
	public void run() {
		count=(count+1)%2;
		 System.out.println("bombing!");
		 new Timer().schedule(new MTimerTask(), 2000+2000*count);
		
	}
   
}
