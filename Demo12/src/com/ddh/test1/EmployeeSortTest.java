package com.ddh.test1;

import java.util.Arrays;

public class EmployeeSortTest {
     public static void main(String[] args) {
	  Employee[] staff=new Employee[3];	
	  staff[0]=new Employee("aaaa", 7000);
	  staff[1]=new Employee("bbb", 8000);
	  staff[2]=new Employee("cccc", 5000);
	  Arrays.sort(staff);
	  for(Employee e:staff){
		  System.out.println("name="+e.getName()+",salary="+e.getSalary());
	  }
	}
}
