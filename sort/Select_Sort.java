package com.ddh.sort;

public class Select_Sort {
   public static void select_Sort(int k[],int n){
	   int min,temp,count1 = 0,count2=0;
	   for(int i=0;i<n-1;i++){
		   min=i;
		   for(int j=i+1;j<n;j++){
                count1++;
			   if(k[j]<k[min]){
				   min=j;
			   }
		   }
		   if(min!=i){
			   count2++;
			   temp=k[min];
			   k[min]=k[i];
			   k[i]=temp;
		   }
	   }
	   System.out.println("总共进行了"+count1+"次比较"+count2+"次移动");
   }
   public static void main(String[] args) {
	   int a[]={1,2,6,3,2,5,7,8,8,9};
	   System.out.println("aaaaaaaaaaaa");
		select_Sort(a, a.length);
		System.out.println("排序后的结果如下:");
		for(int i=0;i<a.length;i++){
			 System.out.print(" "+a[i]);
		}
}
   
}
