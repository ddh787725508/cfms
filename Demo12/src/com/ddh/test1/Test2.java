package com.ddh.test1;

import java.util.ArrayList;

public class Test2 {
       public static void main(String[] args) {
	  String s="ok";
	  StringBuilder sb=new StringBuilder(s);
	  System.out.println(s.hashCode()+" "+sb.hashCode()+" "+sb.capacity());
	  String t="ok";
	  StringBuilder tb=new StringBuilder(t);
	  System.out.println(t.hashCode()+" "+tb.hashCode());
	  ArrayList a=new ArrayList<>();
	  a.trimToSize();
	  
  }
}
