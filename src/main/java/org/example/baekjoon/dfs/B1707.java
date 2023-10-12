package org.example.baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1707 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            var st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            var map = new HashMap<Integer, Set<Integer>>();

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;

                map.putIfAbsent(from, new HashSet<>());
                map.putIfAbsent(to, new HashSet<>());
                map.get(from).add(to);
                map.get(to).add(from);
            }

            colors = new int[v];
            result = "YES";
            for (int node = 0; node < v; node++) {
                if (colors[node] == 0) dfs(map, node, 1);
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    static String result;
    static int[] colors;
    static Set<Integer> empty = new HashSet<>();

    static public boolean dfs(Map<Integer, Set<Integer>> map, int node, int color) {
        if (colors[node] != 0) return false;

        colors[node] = color;
        for (var next : map.getOrDefault(node, empty)) {
            if (colors[next] == color || dfs(map, next, color * -1)) {
                result = "NO";
                return true;
            }
        }
        return false;
    }
}