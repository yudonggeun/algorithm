package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj2342 {

    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = Integer.MAX_VALUE;
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = input.length;
        // init
        dp = new int[size + 1][5][5];
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, Integer.MAX_VALUE);
            }
        }
        dp[0][0][0] = 0;

        for (int i = 0; i < size; i++) {
            int target = input[i];
            for (int left = 0; left < 5; left++) {
                for (int right = 0; right < 5; right++) {
                    if (dp[i][left][right] != Integer.MAX_VALUE) {
                        if (left != target)
                            dp[i + 1][left][target] = Math.min(dp[i][left][right] + cost(right, target), dp[i + 1][left][target]);
                        if (right != target)
                            dp[i + 1][target][right] = Math.min(dp[i][left][right] + cost(left, target), dp[i + 1][target][right]);
                    }
                }
            }
        }

        for (int[] fin : dp[size - 1]) {
            for (int target : fin) {
                answer = Math.min(answer, target);
            }
        }
        System.out.println(answer);
    }

    public static int cost(int from, int to) {
        if (from == to) return 1;
        else if (from == 0) return 2;
        else if(Math.abs(from - to) == 2) return 4;
        return 3;
    }
}
