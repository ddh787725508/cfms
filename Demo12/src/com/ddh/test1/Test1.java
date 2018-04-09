package com.ddh.test1;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Test1 {
   public static void main(String[] args) throws IOException {
//	  double x=4;
//	  double y=Math.sqrt(x);
//	  
//	   System.out.println(" the result is:"+y);
//      System.out.println(Math.E+" "+Math.PI);
//	   double x =9.997;
//	   int nx=(int)x;
//	   System.out.println(nx);
//   
//	   String greeting="Hello";
//	   System.out.println(greeting=="Hello");
//	   System.out.println(greeting.substring(0, 3)=="Hel");
//	 int count=greeting.codePointCount(0, greeting.length());
//	   System.out.println(count);
//	   Console cons=System.console();
//	   String username=cons.readLine("User name:");
//	   char[] passwd=cons.readPassword("Password:");
//	   System.out.println("username:"+username+" "+"password:"+passwd.toString());
//	   Scanner sc=new Scanner(Paths.get("myfile.txt"));
	   
	   //System.out.println(Paths.get("myfile.txt"));
//   
//	   	PrintWriter pw=new PrintWriter("myfile.txt");
//	    pw.write("aaaaaaaaaaaaaaaaaaaa");
//	    pw.flush();
//	   Calendar now=new GregorianCalendar();
//	   int month=now.get(Calendar.MONTH);
//	   int weekday=now.get(Calendar.DAY_OF_WEEK);
//	   System.out.println(month+"£º"+weekday);
//	   now.set(Calendar.YEAR, 2016);
//	  System.out.print(now.get(Calendar.YEAR));
//	   Calendar time=new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
//	   Date hireDate=time.getTime();
////	   System.out.println(hireDate);
//	   	NumberFormat cFormat=NumberFormat.getCurrencyInstance();
//	   	NumberFormat numberFormat=NumberFormat.getPercentInstance();
//	   	double x=0.1;
//	   	System.out.println(cFormat.format(x));
//	   	System.out.println(numberFormat.format(x));

       int i=-2000000000;
       System.out.println(i>>>30);
   }
}
