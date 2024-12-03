package org.example.baekjoon.FloydWarshall;
// https://www.acmicpc.net/problem/2458

import java.io.*;
import java.util.*;

public class Bj2458 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int n = input[0];
        int m = input[1];

        Map<Integer, HashSet<Integer>> downGraph = new HashMap<>();
        Map<Integer, HashSet<Integer>> upGraph = new HashMap<>();

        // set direct graph
        for (int i = 0; i < m; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int small = input[0];
            int big = input[1];

            downGraph.putIfAbsent(small, new HashSet<>());
            downGraph.get(small).add(big);

            upGraph.putIfAbsent(big, new HashSet<>());
            upGraph.get(big).add(small);
        }

        // dfs
        int answer = 0;
        for (int node = 1; node <= n; node++) {
            dfs(downGraph, node, new boolean[n + 1]);
            dfs(upGraph, node, new boolean[n + 1]);
            if (nodeCount == n + 1) {
                answer++;
            }
            nodeCount = 0;
        }
        System.out.println(answer);
    }

    static int nodeCount = 0;

    public static void dfs(Map<Integer, HashSet<Integer>> graph, int node, boolean[] visited) {

        if (visited[node]) return;
        visited[node] = true;
        nodeCount++;

        for (Integer nextNode : graph.getOrDefault(node, new HashSet<>())) {
            dfs(graph, nextNode, visited);
        }
    }
}
