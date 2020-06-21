package com.lxg.springboot.test.huawetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IptoInt {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String message = "";
        while ((message = br.readLine()) !=null){
            String ip = message;
            String intstr = br.readLine();
            ipToInt(ip);
            intToIp(intstr);
        }
    }

    private static void ipToInt(String ip){
        if (isValidIp(ip)){
            String [] dataIp = ip.split("\\.");
            String sb = Arrays.stream(dataIp).map(e -> binaryString(e,7)).collect(Collectors.joining());
            System.out.println(Long.parseLong(sb,2));
            Arrays.stream(dataIp).map(e->Integer.valueOf(e)).sorted(Integer::compareTo);
        }
    }

    private static void intToIp(String intstr){
        String sb = binaryString(intstr,31);
        String [] dataIp = IntStream.range(0, 4).mapToObj(i -> sb.substring(i * 8, i * 8 + 8)).toArray(String[]::new);
        StringBuilder sbs = new StringBuilder();
        Arrays.stream(dataIp).forEach(n -> sbs.append(Integer.parseInt(n, 2)).append("."));
        System.out.println(sbs.substring(0,sbs.length()-1));

    }

    private static boolean isValidIp(String ip){
        boolean res;
        if (ip == null || "".equals(ip))
            return false;
        Pattern pattern = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)$");
        Matcher matcher = pattern.matcher(ip);
        if (matcher.matches()){
            String [] str = ip.split("\\.");
            res = Arrays.stream(str).mapToInt(Integer::parseInt).noneMatch(num -> num < 0 || num > 255);
        }else {
            res = false;
        }
        return res;
    }

    private static String binaryString(String num,int n){

        StringBuilder sb = new StringBuilder();
        for (int i=n; i>=0; i--){
            sb.append(Long.parseLong(num)>>i&1);
        }
        return sb.toString();
    }
}
