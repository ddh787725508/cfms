package com.ddh.test1;

public class CloneTest {
   public static void main(String[] args) {
	try {
		Employee original=new Employee("aaaaaaa",333333);
		original.setHireDay(2000, 1, 22);
		Employee copy=original.clone();
		copy.raiseSalary(10);
		copy.setHireDay(2000, 12, 1);
		System.out.println("original="+original);
		System.out.println("copy="+copy);
	} catch (CloneNotSupportedException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	
}
}
