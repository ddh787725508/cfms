package com.ddh.decorate;

public class TrouserFinery extends Finery {  
	  
    public TrouserFinery(People people){  
        super(people);  
    }  
    @Override  
    public void wearClothing() {  
        people.wearClothing();  
        System.out.println("******´©Î÷·þ¿ã*******");  
    }  
      
}  