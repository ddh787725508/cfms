package com.ddh.test1;

 

public class StaticInnerClassTest {
    public static void main(String[] args) {
		double[] d=new double[20];
		for(int i=0;i<d.length;i++){
			d[i]=100*Math.random();
		}
    ArrayAlg.Pair a= ArrayAlg.minmax(d);
    System.out.println("min="+a.getFirst());
    System.out.println("max="+a.getSecond());
	}
}
