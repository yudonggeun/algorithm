package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj2533 {

    public static List<Integer> empty = new ArrayList<>();
    public static Map<Integer, List<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(to);

            map.putIfAbsent(to, new ArrayList<>());
            map.get(to).add(from);
        }

        dp = new int[n + 1][];
        process(1, 0);
        System.out.println(Math.min(dp[1][1], dp[1][0]));
    }

    public static int[][] dp;

    public static void process(int node, int preNode) {
        if (dp[node] != null) {
            return;
        }

        int[] acc = new int[]{0, 1};

        for (Integer next : map.getOrDefault(node, empty)) {
            if (next == preNode) continue;
            process(next, node);
            acc[0] += dp[next][1];
            acc[1] += Math.min(dp[next][0], dp[next][1]);
        }
        dp[node] = acc;
    }
}
