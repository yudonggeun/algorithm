package org.example.baekjoon.level.gold.five;

import java.io.*;
import java.util.*;

public class Bj9084 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            int coinCount = Integer.parseInt(br.readLine());

            int[] coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int targetPrice = Integer.parseInt(br.readLine());

            int[] dp = new int[targetPrice + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int price = 0; price <= targetPrice; price++) {
                    if (price - coin >= 0) {
                        dp[price] += dp[price - coin];
                    }
                }
            }

            System.out.println(dp[targetPrice]);
        }
    }
}
