package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj2623 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> map = new HashMap<>();

        int[] degree = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int lastIndex = input[0];
            for (int idx = 1; idx < lastIndex; idx++) {
                int small = input[idx];
                int large = input[idx + 1];
                degree[large]++;
                map.putIfAbsent(small, new ArrayList<>());
                map.get(small).add(large);
            }
        }

        // calculate
        int count = 0;
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            Integer t = queue.poll();
            count++;
            sb.append(t).append("\n");

            for (Integer next : map.getOrDefault(t, new ArrayList<>())) {
                degree[next]--;
                if (degree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        // print
        if (count == n) {
            bw.write(sb.toString());
        } else {
            bw.write("0");
        }
        bw.flush();
    }
}
