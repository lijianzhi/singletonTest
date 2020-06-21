package com.lxg.springboot.test.huawetest;

import java.util.Arrays;
import java.util.Scanner;

public class Strmerage {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            StringBuffer rs=new StringBuffer();
            char[] strmx=(sc.next()+sc.next()).toCharArray();
            String string1="";
            String string2="";
            for(int i=0;i<strmx.length;i++){
                if(i%2==0){
                    string1+=strmx[i];
                }else {
                    string2+=strmx[i];
                }
            }
            char[] str1=string1.toCharArray();
            char[] str2=string2.toCharArray();
            Arrays.sort(str1);
            Arrays.sort(str2);
            String strx="";
            int k=0;
            for(int i=0;i<Math.min(str1.length,str2.length);i++){
                strx+=str1[i];
                strx+=str2[i];
                if(i==Math.min(str1.length,str2.length)-1){
                    k=i;
                }
            }
            if(str1.length>str2.length){
                strx+=str1[k+1];
            }else if(str1.length<str2.length) {
                strx+=str2[k+1];
            }
            char[] str=strx.toCharArray();
            for (int i=0;i<str.length;i++){
                if(String.valueOf(str[i]).matches("[A-Fa-f]")){
                    String res=revser(Integer.toBinaryString(Integer.valueOf(Character.toLowerCase(str[i])) - 87));
                    int x=Integer.parseInt(res,2);
                    rs.append(Nx(x));
                }else if(String.valueOf(str[i]).matches("[0-9]")){
                    String res="";
                    String hex=Integer.toBinaryString(Integer.parseInt(String.valueOf(str[i])));
                    if(hex.length()<4){
                        for(int j=0;j<4-hex.length();j++){
                            res+="0";
                        }
                    }
                    String resx=revser(res+hex);
                    int x=Integer.parseInt(resx, 2);
                    rs.append(Nx(x));
                }else {
                    rs.append(str[i]);
                }
            }
            System.out.println(rs);

        }

    }
    public  static String revser(String srx){
        StringBuffer sb=new StringBuffer();
        return sb.append(srx).reverse().toString();
    }
    public static String Nx(int x){
        if(x==10){
            return "A";
        }else if(x==11){
            return "B";
        }else if(x==12){
            return "C";
        }else if(x==13){
            return "D";
        }else if(x==14){
            return "E";
        }else if(x==15){
            return "F";
        }
        return String.valueOf(x);
    }


}
