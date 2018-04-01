package com.ddh.sort;

public class SortTest {
public static void QuickSort(int k[],int n){
	 QSort(k,0,n-1);
}

private static void QSort(int[] k, int low, int high) {
	 int point;
	 if(low<high){
		 point=Partition(k,low,high);
		 QSort(k, low, point-1);
		 QSort(k,point+1,high);
	 }
}

private static int Partition(int[] k, int low, int high) {
	// TODO 自动生成的方法存根
	int point=k[low];
	while(low<high && k[high]>=point){
		high--;
	}
	Swap(k,low,high);
	while(low<high && k[low]<=point){
		low++;
	}
	return low;
}

private static void Swap(int[] k, int low, int high) {
	 int temp;
	 temp=k[low];
	 k[low]=k[high];
	 k[high]=temp;	
}
public static void main(String[] args) {
	  int a[]={1,2,6,3,2,5,7,8,8,9};
	   System.out.println("aaaaaaaaaaaa");
		 QuickSort(a, a.length);
		System.out.println("排序后的结果如下:");
		for(int i=0;i<a.length;i++){
			 System.out.print(" "+a[i]);
		}
}



}
