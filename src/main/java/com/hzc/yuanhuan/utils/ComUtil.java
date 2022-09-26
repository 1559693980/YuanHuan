package com.hzc.yuanhuan.utils;

public class ComUtil {


    public static boolean isNotNull(Integer integer){
        return integer == null;
    }

    public static boolean isNotNull(String s){
        if (s == null ){
            return true;
        }
        return s.trim().equals("");
    }
}
