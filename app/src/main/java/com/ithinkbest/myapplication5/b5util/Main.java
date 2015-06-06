package com.ithinkbest.myapplication5.b5util;

public class Main {
	public static void main(String[] args) {
//		int[] case1={10,11,12,13,14};
//		int[] case1={-123,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
//		int[] case1={987,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};		
//		int[] case1={0,0,0,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
		int[] case1={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
		Board5x5 b=new Board5x5Counter();
//		int not_yet_set_array=b.getLineCount();
//		System.out.println("Not yet set array=>"+not_yet_set_array);
		//
		b.setChecked(case1);
		int result1=b.getLineCount();
		System.out.println("case1=>"+result1);	
	}
}
