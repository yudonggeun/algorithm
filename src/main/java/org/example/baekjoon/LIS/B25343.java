package org.example.baekjoon.LIS;
//https://www.acmicpc.net/problem/25343j

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B25343 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {

            Arrays.fill(dp[i], 1);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                dp[row][col] = getMin(map, dp, row, col);
            }
        }

        int answer = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                answer = Math.max(answer, dp[row][col]);
            }
        }
        System.out.println(answer);
    }

    private static int getMin(int[][] map, int[][] dp, int row, int col) {
        int targetValue = map[row][col];
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if(map[i][j] < targetValue){
                    dp[row][col] = Math.max(dp[i][j] + 1, dp[row][col]);
                }
            }
        }
        return dp[row][col];
    }
}