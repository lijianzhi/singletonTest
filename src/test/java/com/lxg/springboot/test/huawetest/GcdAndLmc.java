package com.lxg.springboot.test.huawetest;


import java.util.Scanner;

public class GcdAndLmc {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            int m = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(gcd(m,n));
        }
    }
    private static int gcd(int m, int n){
        int fg = 0;
        int min = Math.min(m, n);
        for (int i = min; i >= 1; i--) {
            if (m % i == 0 && n % i == 0) {
                fg = m * n / i;
                break;
            }
        }
        return fg;
    }

}
