package com.ephotocloud.app5x5.util;

public class Board5x5Counter implements Board5x5 {
    private static String LOG_TAG = "MARK987";

    int lineCnt = ERROR_NOT_IN_CATEGORY;
    int[] cells;
    int[] vals;
    int addedVal;

    @Override
    public int getAddedValue() {
        return addedVal;
    }

    public Board5x5Counter() {

        cells = new int[25];
    }


    @Override
    public String getTextBoard(int style) {
        if (style == STYLE_1) {
            return getTextBoard1();
        }
        if (style == STYLE_2) {
            return getTextBoard2();
        }
        if (style == STYLE_3) {
            return getTextBoard3();
        }
        if (style == STYLE_4) {
            return getTextBoard4();
        }
        return "";
    }

    public String getTextBoard1() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 25; i++) {
            if (cells[i] == 1) {
                sb.append(" X");
            } else {
                sb.append(" .");
            }
            if (((1 + i) % 5) == 0) {
                sb.append("\n");
            }
        }
        sb.append("lines:" + lineCnt);
        return sb.toString();
    }

    public String getTextBoard2() {
      //  String strAbc = "@ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        //    sb.append("\nAndroid\n");
        for (int i = 0; i < 25; i++) {
            if (cells[i] == 1) {
                String str = String.format("%02d", vals[i]);
                sb.append(" " + str);
            } else {
                sb.append("  .");

            }
            if (((1 + i) % 5) == 0) {
                sb.append("\n");

            }
        }
        sb.append("lines:" + lineCnt);
        return sb.toString();
    }

    public String getTextBoard4() {
        String strAbc = "@ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        //    sb.append("\nAndroid\n");
        for (int i = 0; i < 25; i++) {
            if (cells[i] == 1) {
                String str = String.format("%s", strAbc.charAt(vals[i]));
                sb.append(" " + str);
            } else {
                sb.append(" .");

            }
            if (((1 + i) % 5) == 0) {
                sb.append("\n");

            }
        }
        return sb.toString();
    }

    public String getTextBoard3() {
        String strAbc = "@ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        //    sb.append("\nAndroid\n");
        for (int i = 0; i < 25; i++) {
            String str = String.format("%s", strAbc.charAt(vals[i]));
            sb.append(" " + str);
            if (((1 + i) % 5) == 0) {
                sb.append("\n");

            }
        }
        return sb.toString();
    }


    public String xxxgetTextBoard3() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nAndroid\n");
        for (int i = 0; i < 25; i++) {
            String str = String.format("%2d", cells[i]);
            sb.append(" " + str);

            if (((1 + i) % 5) == 0) {
                sb.append("\n");

            }
        }
        return sb.toString();
    }

    @Override
    public int getLineCount() {
        return lineCnt;
    }


    public void computeAddedValue() {

//		int val=0;
        //	int maxVal
        addedVal = 0;


        for (int[] oneLine : lines) {
            int cnt = 0;
            for (int oneCell : oneLine) {
                if (cells[oneCell] == 1) {
                    cnt++;

                }
            }
            //    Log.d(LOG_TAG, " cnt="+cnt+"  lines " + oneLine[0] + " " + oneLine[1] + " " + oneLine[2] + " " + oneLine[3] + " " + oneLine[4] + " ");

            switch (cnt) {
                case 5:
                    addedVal += 100000000;
                    break;
                case 4:
                    addedVal += 1000000;
                    break;
                case 3:
                    addedVal += 10000;
                    break;
                case 2:
                    addedVal += 100;
                    break;
                case 1:
                    addedVal += 1;
                    break;
            }
            //	if ()


        }


    }

    @Override
    public void setChecked(int[] checked) {
        if (checked.length > 25) {
            lineCnt = ERROR_ARRAY_SIZE_TOO_BIG;
            return;
        }
        for (int i = 0; i < checked.length; i++) {
            if (checked[i] < 0) {
                lineCnt = ERROR_ARRAY_VALUE_BELOW_MINUMUN;

                return;
            }
            if (checked[i] > 24) {
                lineCnt = ERROR_ARRAY_VALUE_ABOVE_MAXINUM;

                return;
            }
        }
        for (int i = 0; i < cells.length; i++) {
            cells[i] = -1;
            //	System.out.print(cells[i]+" ");
        }
//		System.out.println();

        int tmp;
        for (int i = 0; i < checked.length; i++) {
            tmp = checked[i];
            cells[tmp] = 1;
            //	System.out.print(tmp+" ");
        }
        //System.out.println();

//		for (int i=0;i<cells.length;i++){
//			//	cells[i]=-1;
//				System.out.print(cells[i]+" ");
//			}
//			System.out.println();


        computeAddedValue();

        // TODO Auto-generated method stub
        lineCnt = 0;
        for (int[] oneLine : lines) {
            boolean lineResult = true;

            for (int oneCell : oneLine) {
//                System.out.print(oneCell + " ");
                if (cells[oneCell] != 1) {
                    lineResult = false;
//                    System.out.print(" --- not line ");
                    break;
                }

                //	if ()
            }
            if (lineResult) {
                lineCnt++;
//				System.out.print(" <== Count it one line ");
            }
//			System.out.println();
            //	if

        }


        //return true;
    }

    @Override
    public void setValueSet(int[] valueSet) {
        vals = valueSet;
    }

}
