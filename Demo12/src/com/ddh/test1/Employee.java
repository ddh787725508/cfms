package com.ddh.test1;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Comparable<Employee> ,Cloneable{
      private String name;
      private double salary;
      private Date hireDay;
//      public Date getHireDay() {
//		return hireDay;
//	}
      public Employee clone() throws CloneNotSupportedException  
      {  
         // call Object.clone()  
         Employee cloned = (Employee) super.clone();  
     
         // clone mutable fields  
         cloned.hireDay = (Date) hireDay.clone();  
     
         return cloned;  
      }  
      public void setHireDay(int year, int month, int day)  
      {  
         Date newHireDay = new GregorianCalendar(year, (month - 1), day).getTime();  
           
         // Example of instance field mutation  
         hireDay.setTime(newHireDay.getTime());  
      }  
	public Employee(String n,double s){
    	  this.name=n;
    	  this.salary=s;
    	  hireDay=new Date();
      }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public int compareTo(Employee o) {
		 return Double.compare(salary, o.salary);
	}
      public void raiseSalary(double byPercent){
    	  double raise=salary*byPercent/100;
    	  salary+=raise;
      }
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", hireDay=" + hireDay + "]";
	}
     
}
