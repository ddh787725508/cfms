package com.ddh.decorate;

public class Xiaoming  implements People{
	private String name;
	public Xiaoming(){
		name="xiaoming";
	}
	@Override
	public void wearClothing() {
		// TODO 自动生成的方法存根
		System.out.println("开始穿衣服...");
	}
}
