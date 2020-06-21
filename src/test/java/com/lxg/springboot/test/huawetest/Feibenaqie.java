package com.lxg.springboot.test.huawetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;


public class Feibenaqie {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        while((str = br.readLine())!=null){
            System.out.println(feiber(Integer.parseInt(str)));
        }
    }

    private static int feiber(int str){
        if (str == 1 || str ==2){
            return 1;
        }else {
            return feiber(str-1)+feiber(str-2);
        }
    }

}
