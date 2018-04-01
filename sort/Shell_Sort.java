package com.ddh.sort;

public class Shell_Sort {
     public static void shell_Sort(int k[],int n){
    	 int j,temp = 0,count1=0,count2=0;
    	 int gap;
    	    gap=n;
    	 do{
    		 gap=gap/3+1;
    	 for(int i=gap;i<n;i++){
    		 if(k[i]<k[i-gap]){
    			 count1++;
    			 temp=k[i];
    		 
    		 for(j=i-gap;k[j]>temp;j-=gap){
    			 count2++;
    			 k[j+gap]=k[j];
    		 }
    		 k[j+gap]=temp;
    		 }
    	 }
    	 }while(gap>1);
    	 System.out.println("总共进行了"+count1+"次比较"+count2+"次移动");
     }
     public static void main(String[] args) {
    	  int a[]={1,2,6,3,2,5,7,8,8,9};
   	   System.out.println("aaaaaaaaaaaa");
   	    shell_Sort(a, a.length);
   		System.out.println("排序后的结果如下:");
   		for(int i=0;i<a.length;i++){
   			 System.out.print(" "+a[i]);
   		}
	}
}
