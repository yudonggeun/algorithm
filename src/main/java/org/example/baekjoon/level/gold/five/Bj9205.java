package org.example.baekjoon.level.gold.five;

import java.io.*;
import java.util.*;

public class Bj9205 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCase; i++) {
            int conventionStoreCount = Integer.parseInt(br.readLine());

            int[] startingPoint = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[][] conventionPoints = new int[conventionStoreCount][];
            for (int j = 0; j < conventionStoreCount; j++) {
                conventionPoints[j] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            int[] endPoints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            String solution = solution(startingPoint, endPoints, conventionPoints);
            System.out.println(solution);
        }
    }

    public static String solution(int[] startingPoint, int[] endPoints, int[][] conventionPoints) {

        List<int[]> points = new ArrayList<>();
        points.add(startingPoint);
        for (int[] point : conventionPoints) points.add(point);
        points.add(endPoints);

        init(points.size());
        for (int i = 0; i < points.size(); i++) {
            int[] pointA = points.get(i);
            for (int j = i + 1; j < points.size(); j++) {
                int[] pointB = points.get(j);

                if (isConnect(pointA[0], pointA[1], pointB[0], pointB[1])) {
                    union(i, j);
                }
            }
        }

        if (find(0) == find(points.size() - 1)) {
            return "happy";
        } else {
            return "sad";
        }
    }

    static int[] parents;

    public static void union(int i, int j) {
        int ri = find(i);
        int rj = find(j);

        if (ri != rj) {
            parents[ri] = rj;
        }
    }

    public static int find(int i) {
        if (parents[i] == i) return i;
        else return parents[i] = find(parents[i]);
    }

    public static void init(int size) {
        parents = new int[size];
        Arrays.setAll(parents, i -> i);
    }

    public static boolean isConnect(int ar, int ac, int br, int bc) {
        int diffMeter = Math.abs(ar - br) + Math.abs(ac - bc);
        return diffMeter <= 20 * 50;
    }
}

