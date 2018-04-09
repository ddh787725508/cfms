package com.ddh.test2;

public class Clienter2 {
     public static void main(String[] args) {
	  PS2 p=new Adapter1(new USBuser());
	  p.isPS2();
	}
}
