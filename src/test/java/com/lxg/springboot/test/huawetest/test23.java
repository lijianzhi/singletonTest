package com.lxg.springboot.test.huawetest;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName test23
 * @Description: TODO
 * @Author 77984
 * @Date 2021/2/5
 * @Version V1.0
 **/
public class test23 {
    public static void main(String[] args) {
        int n = 5;
        int w = 10;
        int g[] = {400,500, 200, 300, 350};
        int p[] = {5, 5, 3, 4, 3};
        System.out.println(getBestGoldMining3(n, w,p, g));
        System.out.println(getBestGoldMining2(n, w,p, g));

        System.out.println(getMostGold(n, w,p, g));
    }
    public static int getBestGoldMining3(int n,int w,int[] p,int[] g){
        int[] resultTable = new int[w+1];
        for (int i = 1; i <= n; i++) {
            for (int j = w; j >=1; j--) {
                if(j>=p[i-1]){
                    //左边的resultTable[j]是i号金矿时的收益，而右边则相当于是i-1号矿收益因为还未被覆盖
                    resultTable[j] = Math.max(resultTable[j],resultTable[j-p[i-1]]+g[i-1]);
                }
            }
        }
        return resultTable[w];
    }

    public static int getMostGold(int n, int w,  int[] p, int[] g) {

//        F (N,W) = 0 （当N <= 1, W < P[0]）;
//
//        F (N,W) = G[0] （当N == 1, W >= P[0]）;
//
//        F (N,W) = F(N-1, W) （当N > 1, W < P[N - 1]）;
//
//        F (N,W) = MAX ( F(N-1, W) , F(N-1，W - P[ N-1]) + G[N-1])（当N > 1, W >= P[N - 1]）;

        int col = w + 1;   //因为F(x,0)也要用到，所以表格应该有w+1列
        int[] preResults = new int[col]; //存放上一行的结果
        int[] results = new int[col]; //存放当前行的结果

        //填充边界格子的值
        for(int i=0; i<=w; i++) {
            if(i < p[0]) {
                preResults[i] = 0;
            }else {
                preResults[i] = g[0];
            }
        }

        //填充其余格子的值，从上一行推出下一行，外层循环是金矿数量，内层循环是工人数
        for(int i=0; i<n; i++) {
            for(int j=0; j<col; j++) {
                if(j < p[i]) {
                    results[j] = preResults[j];
                }else {
                    results[j] = Math.max(preResults[j], preResults[j-p[i]] + g[i]);
                }
            }
            for(int j=0; j<col; j++) {
                preResults[j] = results[j];
            }
        }

        return results[w];
    }


    public static int getBestGoldMining2(int n,int w,int[] p,int[] g){
        //使用二维数组表示表，纵轴是金矿信息，横轴是人数信息
        int[][] resultTable = new int[n+1][w+1];
        //填充表格
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                //当前人数不足以挖g[i-1]矿，就把获得收益置为不挖当前矿的收益
                if(j<p[i-1]){
                    resultTable[i][j] = resultTable[i-1][j];
                }else{
                    /**
                     * 可以挖当前矿，取挖矿与不挖矿的最优解
                     * resultTable[i][j]就代表当前矿的最大收益 = max(不挖当前矿去查看之后的收益，挖当前矿查看之后的收入并加上当前矿的收入)
                     * 为什么resultTable[i][j]后面的却是resultTable[i-1][j-p[i-1]]+g[i-1]
                     * 因为当前循环是从1开始的，g中的i-1、p中的i-1都其实代表的是当前矿的信息
                     */
                    resultTable[i][j] = Math.max(resultTable[i-1][j],resultTable[i-1][j-p[i-1]]+g[i-1]);
                }
            }
        }
        //最后一个格子就是最优收益
        return resultTable[g.length][w];
    }
}
