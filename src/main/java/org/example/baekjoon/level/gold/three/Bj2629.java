package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj2629 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] weights = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int m = Integer.parseInt(br.readLine());
        int[] beads = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        boolean[] dp = new boolean[40_001];
        dp[0] = true;

        for (int weight : weights) {
            for (int i = dp.length - 1; i >= weight; i--) {
                dp[i] = dp[i] || dp[i - weight];
            }
        }
        for (int weight : weights) {
            for (int i = 0; i < dp.length - weight; i++){
                dp[i] = dp[i] || dp[i + weight];
            }
        }

        for (int bead : beads) {
            String result = dp[bead] ? "Y " : "N ";
            System.out.print(result);
        }
    }
}
