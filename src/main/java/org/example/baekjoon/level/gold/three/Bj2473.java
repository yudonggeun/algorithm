package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj2473 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        Arrays.sort(input);

        long abs = Long.MAX_VALUE;
        long[] candidates = new long[3];

        for (int i = 0; i < n - 2; i++) {
            long e1 = input[i];

            int start = i + 1;
            int end = n - 1;

            while (start < end) {
                long e2 = input[start];
                long e3 = input[end];

                long sum = e1 + e2 + e3;
                long absCase = Math.abs(sum);
                if (abs > absCase) {
                    abs = absCase;
                    candidates[0] = e1;
                    candidates[1] = e2;
                    candidates[2] = e3;
                }

                if (sum > 0) end--;
                else if (sum < 0) start++;
                else {
                    System.out.println(String.format("%d %d %d", candidates[0], candidates[1], candidates[2]));
                    return;
                }
            }
        }

        System.out.println(String.format("%d %d %d", candidates[0], candidates[1], candidates[2]));
    }
}
