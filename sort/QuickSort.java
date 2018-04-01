package com.ddh.sort;

public class QuickSort {
	final static int MAX_LENGTH_INSERT_SORT=7;
 public static void  quickSort(int k[] ,int n){ 
	 QSort(k,0,n-1);
  }

private static void QSort(int[] k, int low, int high) {
	// TODO 自动生成的方法存根
	int point;
	if(high-low>MAX_LENGTH_INSERT_SORT){
		point=Partiton(k,low,high);
		QSort(k,low, point-1);
		QSort(k,point+1,high);
	}else{
		Insert_Sort(k,low,high);
	}
}

private static int Partiton(int[] k,int low,int high) {
	int point;
	int m=low+(high - low)/2;
	if(k[low]>k[high]){
		swap(k,low,high);
	}
	if(k[m]>k[high]){
		swap(k,low,high);
	}
	if(k[m]>k[low]){
		swap(k,low,high);
	}
	
	point=k[low];
	while(low < high){
		while(low < high&& k[high]>=point){
			high--;
		}
 
		k[low]=k[high];
		while(low < high && k[low]<=point){
			low++;
		}
		k[high]=k[low];
	}
	k[low]=point;
	return low;
}
public static void ISort(int k[],int n){
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

public static void Insert_Sort(int[] k,int low,int high){
	ISort(k, (high-low)+2);
}
private  static  void swap(int[] k, int low, int high) {
	// TODO 自动生成的方法存根
	int temp;
	temp=k[low];
	k[low]=k[high];
	k[high]=temp;
}
	public static void main(String[] args) {
		  int a[]={9,8,7,6,5,4,3,2,1,0};
		   System.out.println("aaaaaaaaaaaa");
			 quickSort(a, a.length);
			System.out.println("排序后的结果如下:");
			for(int i=0;i<a.length;i++){
				 System.out.print(" "+a[i]);
			}
	}

}
