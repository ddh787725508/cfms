package com.ddh.test2;

public class Adapter extends USBuser implements PS2 {
@Override
public void isPS2() {
	isUSB();
	
}
}
