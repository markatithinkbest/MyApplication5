package com.ephotocloud.app5x5.util;

import java.util.Random;

/**
 * Created by u1 on 2015/5/19.
 */
public class B253 {
    int val[];

    public B253() {
        val=new int[25];
        for (int i=0;i<25;i++){
            val[i]=1+i;
        }
    }
    public int getPosition(int v){
        for (int i=0;i<25;i++){
            if (val[i]==v){
                return i;
            }
        }
        return -1; // err
    }
    public int[] getValArray(){
        return val;
    }
    public void makeRandomSet() {

        //char[] toRandom = boardVal.toCharArray();
        //  char[] toRandom = Info.INIT_NUM_SET.toCharArray();

        Random rnd = new Random();
        int tmp1 ;
        int tmp2 ;
        int swap ;
        String strTmp = "";
        for (int i = 0; i < 100; i++) {
            tmp1 = rnd.nextInt(25);// get 0,1,2,...,23,24
            tmp2 = rnd.nextInt(25);

            swap = val[tmp1];
            val[tmp1] = val[tmp2];
            val[tmp2] = swap;
            //  strTmp+=tmp+", ";
        }
    }
        @Override
    public String toString(){
         StringBuilder sb=new StringBuilder();
            sb.append("\n");
        for (int i=0;i<25;i++){
            String str=String.format("%2d",val[i]);
            sb.append(" "+str);

            if (((1+i) % 5)==0){
                sb.append("\n");

            }
        }
        return sb.toString();
    }
}
