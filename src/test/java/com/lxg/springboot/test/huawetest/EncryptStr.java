package com.lxg.springboot.test.huawetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class EncryptStr {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String message = "";
        while ((message = br.readLine()) !=null){
            String key = message;
            String str = br.readLine();
            encrypt(key,str);
        }
    }
    private static void encrypt(String key, String str){
        char [] keys = key.toCharArray();
        Set<Character> set = new LinkedHashSet<>();
        Map<Character,Character> map = new HashMap<>();
        for (int i =0; i< keys.length;i++){
            set.add(keys[i]);
        }
        for (int i =0; i< 26; i++){
            set.add((char)(i+'a'));
        }
        set.toArray();
        Iterator it = set.iterator();
        char [] ch = new char[set.size()];
        for (int i =0;i<ch.length&&it.hasNext();i++){
            ch[i] = (char) it.next();
        }
        StringBuilder sb = new StringBuilder();
        for (int i =0; i<str.length();i++){
            char a = str.charAt(i);
            if (Character.isLowerCase(a)){
                sb.append(Character.toLowerCase(ch[a-'a']));
            }else {
                sb.append(Character.toUpperCase(ch[a-'A']));
            }
        }
        System.out.println(sb);
    }
}
