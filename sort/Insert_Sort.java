package com.ddh.sort;

public class Insert_Sort {
     public static void insert_Sort(int k[],int n){
    	 for(int i=1;i<n;i++){
    		 int get=k[i];
    		 int j=i-1;
    		 while(j>=0&&k[j]>get){
    			 k[j+1]=k[j];
    			 j--;
    		 }
    		 k[j+1]=get;
    	 }
     }
     public static void main(String[] args) {
    	  int a[]={5,2,6,0,3,9,1,7,4,8};
   	    System.out.println("aaaaaaaaaaaa");
   		insert_Sort(a, a.length);
   		System.out.println("排序后的结果如下:");
   		for(int i=0;i<a.length;i++){
   			 System.out.print(" "+a[i]);
   		}
	}
}
