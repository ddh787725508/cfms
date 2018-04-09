package com.ddh.proxy;

public class Xiaohong implements People {

	private People people;
	public Xiaohong(People people){
		this.people=people;
	}
	@Override
public void buyFood() {
	// TODO 自动生成的方法存根
	System.out.println("帮别人去买饭");
	people.buyFood();
}
}
