package com.ddh.proxy;

public class Client {
     public static void main(String[] args) {
		People xiaoming=new Xiaoming();
		People xiaohong=new Xiaohong(xiaoming);
		xiaohong.buyFood();
	}
}
