package com.lxg.springboot.test.huawetest;

import java.util.Scanner;

public class StrReverse {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String str = sc.nextLine();
            StringBuilder sb = new StringBuilder(str).reverse();
            System.out.println(sb.toString());
        }
    }
}
