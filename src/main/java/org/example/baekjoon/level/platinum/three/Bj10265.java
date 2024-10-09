package org.example.baekjoon.level.platinum.three;

import java.io.*;
import java.util.*;

public class Bj10265 {

    static int[] parents;
    static int[] sizes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int totalNumberOfPeople = input[0];
        int maximumCapacity = input[1];

        int[] dependency = Arrays.stream(br.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s) - 1)
                .toArray();

        // step 1

        isCircle = new boolean[totalNumberOfPeople];
        for (int i = 0; i < totalNumberOfPeople; i++) {
            initCircle(i, dependency, new boolean[totalNumberOfPeople]);
        }

        parents = new int[totalNumberOfPeople];
        Arrays.setAll(parents, i -> i);
        sizes = new int[totalNumberOfPeople];
        Arrays.fill(sizes, 1);
        for (int i = 0; i < totalNumberOfPeople; i++) {
            int target = dependency[i];
            if (isCircle[i]) {
                union(i, target);
            }
        }

        for (int i = 0; i < totalNumberOfPeople; i++) {
            dependency[i] = find(dependency[i]);
        }

        // step 2

        Queue<Integer> candiates = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < totalNumberOfPeople; i++) {
            if (i == find(i) && isCircle[i]) {
                queue.add(i);
            } else {
                candiates.add(i);
            }
        }

        Queue<Integer> sortedQueue = new LinkedList<>();

        while (!queue.isEmpty()) {
            int target = queue.poll();

            sortedQueue.add(target);
            Queue<Integer> nextCandiates = new LinkedList<>();
            while (!candiates.isEmpty()) {
                int i = candiates.poll();
                if (i == find(i) && dependency[i] == target) {
                    queue.add(i);
                } else {
                    nextCandiates.add(i);
                }
            }
            candiates = nextCandiates;
        }
        // step 3

        boolean[] dp = new boolean[totalNumberOfPeople + 1];
        dp[0] = true;
        boolean[][] visited = new boolean[totalNumberOfPeople + 1][totalNumberOfPeople];

        for (int capacity = 1; capacity <= maximumCapacity; capacity++) {
            for (Integer i : sortedQueue) {
                int index = i;
                int weight = sizes[index];
                int dependOn = dependency[index];

                if (capacity < weight) continue;
                if (dp[capacity] || !dp[capacity - weight]) continue;
                if (visited[capacity - weight][index]) continue;
                if (isCircle[index] || visited[capacity - weight][dependOn]) {
                    dp[capacity] = true;
                    visited[capacity] = visited[capacity - weight].clone();
                    visited[capacity][index] = true;
                }
            }
        }

        for (int answer = totalNumberOfPeople; answer >= 0; answer--) {
            if (dp[answer]) {
                System.out.println(answer);
                break;
            }
        }
    }

    static boolean[] isCircle;

    public static void initCircle(int i, int[] dependency, boolean[] isVisit) {
        if (isCircle[i]) return;
        if (isVisit[i]) {
            isCircle[i] = true;
            return;
        }

        isVisit[i] = true;
        initCircle(dependency[i], dependency, isVisit);
    }

    public static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    public static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);

        if (ra == rb) return;
        if (sizes[ra] < sizes[rb]) {
            sizes[ra] += sizes[rb];
            parents[rb] = ra;
        } else {
            sizes[rb] += sizes[ra];
            parents[ra] = rb;
        }
    }
}
