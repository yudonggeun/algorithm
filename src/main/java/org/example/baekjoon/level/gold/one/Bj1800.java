package org.example.baekjoon.level.gold.one;

import java.io.*;
import java.util.*;

public class Bj1800 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int i = 1; i <= p; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            map.putIfAbsent(a, new HashMap<>());
            map.putIfAbsent(b, new HashMap<>());

            Map<Integer, Integer> aMap = map.get(a);
            Map<Integer, Integer> bMap = map.get(b);

            aMap.putIfAbsent(b, Integer.MAX_VALUE);
            bMap.putIfAbsent(a, Integer.MAX_VALUE);

            aMap.computeIfPresent(b, (key, val) -> Math.min(val, price));
            bMap.computeIfPresent(a, (key, val) -> Math.min(val, price));
        }

        // logic

        int answer = Integer.MAX_VALUE;

        PriorityQueue<Argument> queue = new PriorityQueue<>(Comparator.comparing(o -> o.price));
        queue.add(new Argument(1, 0, k));
        int[][] visited = new int[n + 1][k + 1];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Arrays.fill(visited[1], 0);

        while (!queue.isEmpty()) {
            Argument argument = queue.poll();
            int node = argument.node;
            int price = argument.price;
            int free = argument.free;

            if (node == n) {
                answer = Math.min(price, answer);
                break;
            }

            Map<Integer, Integer> iter = map.getOrDefault(node, new HashMap<>());
            for (Integer nextNode : iter.keySet()) {
                int nextPrice = Math.max(iter.get(nextNode), price);

                if (visited[nextNode][free] > nextPrice) {
                    visited[nextNode][free] = nextPrice;
                    queue.add(new Argument(nextNode, nextPrice, free));
                }
                if (free > 0 && visited[nextNode][free - 1] > price) {
                    visited[nextNode][free - 1] = price;
                    queue.add(new Argument(nextNode, price, free - 1));
                }
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static class Argument {
        int node;
        public int price;
        int free;

        Argument(int node, int price, int free) {
            this.node = node;
            this.price = price;
            this.free = free;
        }
    }
}
