package com.ithinkbest.myapplication5.b5util;

public interface Board5x5 {
	int[] line0={0,1,2,3,4};
	int[] line1={5,6,7,8,9};
	int[] line2={10,11,12,13,14};
	int[] line3={15,16,17,18,19};
	int[] line4={20,21,22,23,24};
	int[] line5={0,5,10,15,20};
	int[] line6={1,6,11,16,21};
	int[] line7={2,7,12,17,22};
	int[] line8={3,8,13,18,23};
	int[] line9={4,9,14,19,24};
	int[] line10={0,6,12,18,24};
	int[] line11={4,8,12,16,20};
	int[][] lines={
			line0,
			line1,
			line2,
			line3,
			line4,
			line5,
			line6,
			line7,
			line8,
			line9,
			line10,
			line11,
	};
	int getLineCount();
	int getAddedValue();

	int ERROR_NOT_IN_CATEGORY=-999;
	int ERROR_ARRAY_SIZE_TOO_BIG=-998;
	int ERROR_ARRAY_VALUE_BELOW_MINUMUN=-997;
	int ERROR_ARRAY_VALUE_ABOVE_MAXINUM=-996;

	int STYLE_1=1;
	int STYLE_2=2;
	int STYLE_3=3;
	int STYLE_4=4;
	void setChecked(int[] checked);
	void setValueSet(int[] valueSet);

	String getTextBoard(int style);
}
