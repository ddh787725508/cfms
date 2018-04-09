package com.ddh.decorate;

public class ShietFinery extends Finery {
 public ShietFinery(People people) {
      super(people);
}
 @Override
	public void wearClothing() {
		// TODO 自动生成的方法存根
		people.wearClothing();
		System.out.println("****穿衬衫********");
	}
}
