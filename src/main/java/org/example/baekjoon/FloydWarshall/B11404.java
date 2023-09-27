package org.example.baekjoon.FloydWarshall;
//https://www.acmicpc.net/problem/11404

import java.io.*;
import java.util.StringTokenizer;

public class B11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cityCount = Integer.parseInt(br.readLine());
        int busCount = Integer.parseInt(br.readLine());
        int INF = 100_000 * cityCount;

        int[][] dp = new int[cityCount][cityCount];
        for (int i = 0; i < cityCount; i++) {
            for (int j = 0; j < cityCount; j++) {
                if (i != j) dp[i][j] = INF;
            }
        }
        for (int i = 0; i < busCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            dp[from][to] = Math.min(dp[from][to], cost);
        }

        for (int m = 0; m < cityCount; m++) {
            for(int from = 0; from < cityCount; from++){
                for(int to = 0; to < cityCount; to++){
                    dp[from][to] = Math.min(dp[from][to], dp[from][m] + dp[m][to]);
                }
            }
        }

        for(int i = 0; i < cityCount; i++){
            for(int j = 0; j < cityCount; j++){
                int value = dp[i][j] == INF ? 0 : dp[i][j];
                bw.write(value + " ");
            }
            bw.write("\n");
        }
        bw.flush();
    }
}