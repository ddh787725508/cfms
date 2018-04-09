package com.ddh.decorate;

public class ShoesFinery extends Finery {  
	  
    public ShoesFinery(People people){  
        super(people);  
    }  
    @Override  
    public void wearClothing() {  
        people.wearClothing();  
        System.out.println("******´©Æ¤Ð¬*******");  
    }  
      
}  