package org.example.programmers.Level3;

import java.util.Arrays;

public class Gps {

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {

        int[][] map = new int[n + 1][n + 1];
        for (int[] edge : edge_list) {
            map[edge[0]][edge[1]] = 1;
            map[edge[1]][edge[0]] = 1;
        }

        for (int i = 0; i < n + 1; i++) {
            map[i][i] = 1;
        }

        // i위치에 j도시가 되기 위한 최소 수정 횟수
        int[][] dp = new int[k][n + 1];
        for (int[] d : dp) Arrays.fill(d, Integer.MAX_VALUE);

        int start = gps_log[0];
        int end = gps_log[k - 1];
        dp[0][start] = 0;

        for (int i = 0; i < k - 1; i++) {
            for (int city = 1; city < n + 1; city++) {
                if (dp[i][city] == Integer.MAX_VALUE) continue;
                for (int next = 1; next < n + 1; next++) {
                    if (map[city][next] == 1) {
                        int change = next == gps_log[i + 1] ? 0 : 1;
                        dp[i + 1][next] = Math.min(dp[i + 1][next], dp[i][city] + change);
                    }
                }
            }
        }

        if (dp[k - 1][end] == Integer.MAX_VALUE) return -1;
        return dp[k - 1][end];
    }
}