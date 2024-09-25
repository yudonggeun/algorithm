package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj1005 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcases = Integer.parseInt(br.readLine());

        for (int i = 0; i < testcases; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int n = input[0];
            int k = input[1];

            int[] times = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // topological sorting
            int[] degrees = new int[n];
            // graph infos
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            for (int j = 0; j < k; j++) {
                input = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                int from = input[0] - 1;
                int to = input[1] - 1;

                degrees[to]++;
                graph.putIfAbsent(from, new HashSet<>());
                graph.get(from).add(to);
            }

            int target = Integer.parseInt(br.readLine()) - 1;

            // calculate this
            int answer = logic(graph, times, degrees, target);
            // print answer
            System.out.println(answer);
        }
    }

    public static int logic(Map<Integer, Set<Integer>> graph, int[] times, int[] degrees, int target) {

        // buffer
        int[] dp = times.clone();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < degrees.length; i++) {
            // if degree is zero, the node is not root node.
            if (degrees[i] != 0) {
                dp[i] = -1;
            } else {
                // if this node is root, add queue
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int from = queue.poll();
            int currentValue = dp[from];

            for (Integer to : graph.getOrDefault(from, new HashSet<>())) {
                int nextValue = currentValue + times[to];
                dp[to] = Math.max(dp[to], nextValue);
                degrees[to]--;
                if (degrees[to] == 0) {
                    queue.add(to);
                }
            }
        }

        return dp[target];
    }
}