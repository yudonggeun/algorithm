package org.example.baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B11400 {

    static Map<Integer, Set<Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map.putIfAbsent(from, new HashSet<>());
            map.putIfAbsent(to, new HashSet<>());

            map.get(from).add(to);
            map.get(to).add(from);
        }

        var visit = new HashSet<Integer>();
        var orders = new HashMap<Integer, Integer>();

        for (int i = 1; i <= v; i++) {
            dfs(i, 0, orders);
        }

        System.out.println(result.size());
        for (var s : result) {
            System.out.println(s.from + " " + s.to);
        }
    }

    static Set<Integer> empty = new HashSet<>();
    static Set<Answer> result = new TreeSet<>(Comparator
            .comparingInt((Answer o) -> o.from)
            .thenComparingInt(o -> o.to)
    );
    static int order = 1;

    static int dfs(int here, int parent, Map<Integer, Integer> orders) {
        if (orders.containsKey(here)) {
            return orders.get(here);
        }
        orders.put(here, order++);

        int nodeOrder = orders.get(here);
        int minOrder = nodeOrder;
        for (var there : map.getOrDefault(here, empty)) {
            if (there == parent) continue;

            if (orders.containsKey(there)) {
                minOrder = Math.min(minOrder, orders.get(there));
            } else {
                int subTreeOrder = dfs(there, here, orders);
                if (nodeOrder < subTreeOrder) {
                    int from = Math.min(here, there);
                    int to = Math.max(here, there);
                    result.add(new Answer(from, to));
                }
                minOrder = Math.min(minOrder, subTreeOrder);
            }
        }
        return minOrder;
    }

    static class Answer {
        int from, to;

        public Answer(int from, int to) {
            this.from = Math.min(from, to);
            this.to = Math.max(from, to);
        }
    }
}
