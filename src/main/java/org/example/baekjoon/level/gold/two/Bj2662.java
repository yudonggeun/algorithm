package org.example.baekjoon.level.gold.two;

import java.io.*;
import java.util.*;

public class Bj2662 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int targetMoney = input[0];
        int companyCount = input[1];

        int[][] tables = new int[targetMoney + 1][];
        tables[0] = new int[companyCount + 1];

        for (int i = 1; i <= targetMoney; i++) {
            tables[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] dp = new int[targetMoney + 1];
        int[][] investDp = new int[targetMoney + 1][companyCount];

        for (int money = 1; money <= targetMoney; money++) {
            for (int i = 0; i < companyCount; i++) {
                for (int gap = 1; gap <= money; gap++) {
                    int currentCompanyInvestMoney = investDp[money - gap][i];

                    int currentValue = tables[currentCompanyInvestMoney][i + 1];
                    int nextValue = tables[currentCompanyInvestMoney + gap][i + 1];

                    int benefit = nextValue - currentValue;
                    int nextDp = dp[money - gap] + benefit;

                    if (nextDp > dp[money]) {
                        dp[money] = nextDp;
                        investDp[money] = investDp[money - gap].clone();
                        investDp[money][i] += gap;
                    }

                }
            }
        }

        System.out.println(dp[targetMoney]);
        for (int money : investDp[targetMoney]) {
            System.out.print(money + " ");
        }
    }
}
