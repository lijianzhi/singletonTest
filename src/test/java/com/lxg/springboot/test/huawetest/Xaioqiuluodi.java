package com.lxg.springboot.test.huawetest;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Xaioqiuluodi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()){
            int n = sc.nextInt();
            System.out.println(getToal(n));
            System.out.println(getHight(n));
        }
    }

    private static float getToal(int n){
        DecimalFormat df = new DecimalFormat("0.000");
        float total = Float.parseFloat(df.format(n*((1-Math.pow(0.5,4.0)))/0.5+n));
        return Float.parseFloat(df.format(total));
    }

    private static float getHight(int n){
        float total = (float) (0.5 * n * Math.pow(0.5,4));
        return total;
    }
}
