package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj2252 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> bigSet = new HashMap<>();
        int[] degree = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());

            bigSet.putIfAbsent(small, new ArrayList<>());
            bigSet.get(small).add(big);

            degree[big]++;
        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer target = queue.poll();
            sb.append(target).append(" ");

            // remove map
            List<Integer> biggerThanMe = bigSet.getOrDefault(target, new ArrayList<>());
            for (Integer big : biggerThanMe) {
                degree[big]--;
                if(degree[big] == 0) {
                    queue.add(big);
                }
            }
            bigSet.remove(target);
        }

        System.out.println(sb);
    }
}
