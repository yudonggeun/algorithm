package org.example.baekjoon.level.gold.two;

import java.io.*;
import java.util.*;

public class Bj2211 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> map = new HashMap<>();
        int[][] speeds = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            map.get(a).add(b);
            map.get(b).add(a);
            speeds[a][b] = speed;
            speeds[b][a] = speed;
        }

        for (Integer from : map.keySet()) {
            Collections.sort(map.get(from), Comparator.comparingInt(to -> speeds[from][to]));
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(o -> o.sum));
        boolean[] visited = new boolean[n + 1];

        for (Integer to : map.get(1)) {
            pq.add(new Node(1, to, speeds[1][to]));
        }
        visited[1] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(visited[node.p]) continue;
            visited[node.p] = true;
            count++;
            sb.append(node.pre).append(" ").append(node.p).append("\n");

            for (Integer to : map.get(node.p)) {
                if (visited[to]) continue;
                pq.add(new Node(node.p, to, node.sum + speeds[node.p][to]));
            }
        }

        System.out.println(count);
        System.out.println(sb);

    }

    static class Node {
        int pre;
        int p;
        int sum;

        public Node(int pre, int p, int sum) {
            this.pre = pre;
            this.p = p;
            this.sum = sum;
        }
    }
}
