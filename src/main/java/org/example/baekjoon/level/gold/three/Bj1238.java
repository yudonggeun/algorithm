package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj1238 {

    static Map<Integer, List<Integer>> paths = new HashMap<>();
    static int[][] map;
    static int[][] dp;
    static List<Integer> EMPTY = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        map = new int[n + 1][n + 1];
        dp = new int[n + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int[] row : map) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int from = input[0];
            int to = input[1];
            int value = input[2];
            map[from][to] = value;
            paths.putIfAbsent(from, new ArrayList<>());
            paths.get(from).add(to);
        }

        getMinPath(x, n + 1, false);
        // reverse map
        reverse(n);

        getMinPath(x, n + 1, true);

        int answer = Integer.MIN_VALUE;
        for (int e = 1; e <= n; e++) {
            if (dp[e][x] == Integer.MAX_VALUE) continue;
            if (dp[x][e] == Integer.MAX_VALUE) continue;
            answer = Math.max(answer, dp[x][e] + dp[e][x]);
        }
        System.out.println(answer);
    }

    static void reverse(int n) {
        int[][] temp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                temp[i][j] = map[j][i];
            }
        }
        map = temp;
        Map<Integer, List<Integer>> tempPath = new HashMap<>();
        for (Integer key : paths.keySet()) {
            for (Integer value : paths.get(key)) {
                tempPath.putIfAbsent(value, new ArrayList<>());
                tempPath.get(value).add(key);
            }
        }
        paths = tempPath;
    }

    static void getMinPath(int from, int size, boolean reverse) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.sum));
        pq.add(new Node(from, 0));
        boolean[] visited = new boolean[size];
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.p]) continue;
            visited[node.p] = true;
            if (reverse) dp[node.p][from] = node.sum;
            else dp[from][node.p] = node.sum;

            for (Integer next : paths.getOrDefault(node.p, EMPTY)) {
                int value = map[node.p][next];
                if (!visited[next] && value != Integer.MAX_VALUE) {
                    pq.add(new Node(next, value + node.sum));
                }
            }
        }
    }

    static class Node {
        int p;
        int sum;

        public Node(int p, int sum) {
            this.p = p;
            this.sum = sum;
        }
    }
}
