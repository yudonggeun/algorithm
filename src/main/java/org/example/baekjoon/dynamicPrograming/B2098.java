package org.example.baekjoon.dynamicPrograming;

import java.io.*;

public class B2098 {

    static int[][] dp;
    static int[][] costs;
    static int size;
    static int NOT_SOLVED = 16_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        costs = new int[size][size];
        dp = new int[1 << size][size];

        for (int from = 0; from < size; from++) {
            String[] input = br.readLine().split(" ");
            for (int to = 0; to < size; to++) {
                int target = Integer.parseInt(input[to]);
                costs[from][to] = target;
            }
        }
        System.out.println(tsp(1, 0));
    }

    public static int tsp(int bitmask, int start) {
        if (dp[bitmask][start] != 0) return dp[bitmask][start];
        if (isVisitAll(bitmask)) return existPath(start, 0) ? costs[start][0] : NOT_SOLVED;

        int min = NOT_SOLVED;
        for (int idx = 0; idx < size; idx++) {
            if (!isVisit(bitmask, idx) && existPath(start, idx)) {
                int cost = costs[start][idx];
                int tsp = tsp(bitmask + (1 << idx), idx);
                min = Math.min(min, cost + tsp);
            }
        }
        return dp[bitmask][start] = min;
    }

    static boolean existPath(int start, int idx) {
        return costs[start][idx] != 0;
    }

    static public boolean isVisit(int bitmask, int idx) {
        return (1 << idx & bitmask) != 0;
    }

    static public boolean isVisitAll(int bitmask) {
        return (1 << size) - 1 == bitmask;
    }
}
