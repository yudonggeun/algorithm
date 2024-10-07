package org.example.baekjoon.level.platinum.four;

import java.util.*;
import java.io.*;

public class Bj12920 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int productCount = input[0];
        int maximumWeight = input[1];

        int[] dp = new int[maximumWeight + 1];

        for (int i = 0; i < productCount; i++) {
            int[] product = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int weight = product[0];
            int value = product[1];
            int stock = product[2];
            int count = 1;

            while (stock > 0) {
                count = Math.min(count, stock);

                for (int target = maximumWeight; target >= count * weight; target--) {
                    dp[target] = Math.max(dp[target], dp[target - count * weight] + (count * value));
                }
                stock -= count;
                count *= 2;
            }
        }

        System.out.println(dp[maximumWeight]);
    }
}
