package org.example.baekjoon.spanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B11266 {

    static int[] tree;
    static Map<Integer, Set<Integer>> graph = new HashMap<>();
    static Set<Integer> empty = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        var st = new StringTokenizer(br.readLine());

        var vertexCount = Integer.parseInt(st.nextToken());
        var edgeCount = Integer.parseInt(st.nextToken());

        tree = new int[vertexCount + 1];

        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            var v1 = Integer.parseInt(st.nextToken());
            var v2 = Integer.parseInt(st.nextToken());

            newEdge(v1, v2);
            newEdge(v2, v1);
        }

        for (int v = 1; v <= vertexCount; v++) {
            if (tree[v] == 0) dfs(v, v);
        }

        System.out.println(breakPoints.size());
        for (Integer breakPoint : breakPoints) {
            System.out.print(breakPoint + " ");
        }
    }

    static Set<Integer> breakPoints = new TreeSet<>();

    static int dfs(int node, int root) {
        tree[node] = ++order;
        int minOrder = tree[node];
        int child = 0;
        for (Integer next : graph.getOrDefault(node, empty)) {
            if (tree[next] != 0) {
                minOrder = Math.min(minOrder, tree[next]);
            } else {
                child++;
                int subTreeMinOrder = dfs(next, root);
                if (node != root && subTreeMinOrder == tree[node]) {
                    breakPoints.add(node);
                }
                minOrder = Math.min(minOrder, subTreeMinOrder);
            }
        }

        if (root == node && child > 1) {
            breakPoints.add(node);
        }

        return minOrder;
    }

    static int order = 0;

    static void newEdge(int from, int to) {
        graph.putIfAbsent(from, new HashSet<>());
        graph.get(from).add(to);
    }
}
