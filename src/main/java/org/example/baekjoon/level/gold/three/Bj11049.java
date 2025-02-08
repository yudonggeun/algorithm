package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj11049 {

    static int[][] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        data = new int[n][];
        dp = new int[n][n];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            data[i] = input;
        }

        int answer = answer(0, n - 1);
        System.out.println(answer);
    }

    static int[][] dp;

    static int answer(int s, int e) {
        if (s == e) return 0;
        if (dp[s][e] != Integer.MAX_VALUE) return dp[s][e];
        int result = Integer.MAX_VALUE;
        for (int m = s; m < e; m++) {
            int tem = data[s][0] * data[m][1] * data[e][1] + answer(m + 1, e) + answer(s, m);
            result = Math.min(result, tem);
        }
        return dp[s][e] = result;
    }
}
