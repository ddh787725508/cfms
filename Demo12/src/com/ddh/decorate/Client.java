package com.ddh.decorate;

public class Client {
  public static void main(String[] args) {
	People people=new Xiaoming();
	Finery shirtFinery=new ShietFinery(people);
	Finery trouserFinery=new TrouserFinery(shirtFinery);
    Finery shonesFinery=new ShoesFinery(trouserFinery);
    shonesFinery.wearClothing();
  }
}
