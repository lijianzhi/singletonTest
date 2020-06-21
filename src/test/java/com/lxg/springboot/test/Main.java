package com.lxg.springboot.test;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();//总钱数，价值限制
            int num = in.nextInt();//商品个数，物品数组长度
            int[] v = new int[num]; //价格
            int[] pv = new int[num];
            int[] q = new int[num];
            for (int i = 0; i < num; i++) {
                v[i] = in.nextInt();
                pv[i] = in.nextInt() * v[i];//重要度*价格 =价值
                q[i] = in.nextInt();//主还是附
            }
            System.out.println(solveKS(v, pv, n, q));
        }

    }

    //动态规划
    private static int solveKS(int[] w, int[] v, int capacity, int[] q) {
        //基准条件：如果索引无效或者容量不足，直接返回当前价值0
        int size = w.length;
        if (size == 0) {
            return 0;
        }
        //初始化
        int[][] dp = new int[size][capacity + 1];
        //先保证使用容量为C的背包装第0个物品的价值数据
        for (int i = 0; i < capacity; i++) {
            dp[0][i] = w[0] <= i ? v[0] : 0;
        }

        //列出其他行
        for (int i = 1; i < size; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (q[i] == 0) {
                    if (w[i] <= j) {
                        dp[i][j] = Math.max(dp[i - 1][j], v[i] + dp[i - 1][j - w[i]]);
                    }
                } else { //附件
                    if (w[i] + w[q[i - 1]] <= j) //附件的话 加上主件一起算
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j- w[i]]+ v[i]);
                }
            }
        }
        return dp[size - 1][capacity];
    }

}
