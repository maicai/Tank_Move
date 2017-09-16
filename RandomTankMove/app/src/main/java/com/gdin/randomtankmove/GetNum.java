package com.gdin.randomtankmove;

import java.util.Random;

/**
 * Created by aceracer on 2017/9/15.
 */
public class GetNum {
    public static int getRands(){
        return new Random().nextInt(4)+1;
    }
    public static int getNum(int... arr){
        return arr[new Random().nextInt(arr.length)];
    }
}
