package com.ddh.test2;

public class Adapter1 implements PS2 {
	  private USB usb;
	  public Adapter1(USB usb) {
	        this.usb=usb;
	}
	@Override
	public void isPS2() {
		usb.isUSB();
		
	}
}
