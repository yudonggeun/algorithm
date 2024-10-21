package org.example.baekjoon.level.gold.one;

import java.io.*;
import java.util.*;

public class Bj16991 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        points = new int[n][];
        for (int i = 0; i < n; i++) {
            points[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            allVisit = allVisit << 1;
            allVisit++;
        }

        initDp();

        allVisit--;
        double result = tps(0, allVisit);
        System.out.println(result);
    }

    public static int n;
    public static long allVisit = 0;

    public static int[][] points;

    public static Map<Integer, Map<Long, Double>> dp = new HashMap<>();

    public static double tps(int i, long visit) {

        if (dp(i, visit) != -1) {
            return dp(i, visit);
        }

        for (int j = 1; j < n; j++) {
            long bit = 1L << j;
            if (i == j) continue;
            if ((visit & bit) == bit) {
                double result = tps(j, visit - bit) + distance(points[i], points[j]);
                if (visit - bit == 0) {
                    result += distance(points[j], points[0]);
                }
                setDp(i, visit, result);
            }
        }

        return dp(i, visit);
    }

    public static void initDp() {
        for (int i = 0; i < n; i++) {
            dp.put(i, new HashMap<>());
            dp.get(i).putIfAbsent(0L, 0.0);
        }
    }

    public static void setDp(int i, long visit, double value) {
        dp.get(i).putIfAbsent(visit, value);
        dp.get(i).computeIfPresent(visit, (k, v) -> Math.min(v, value));
    }

    public static double dp(int i, long bitmask) {
        return dp.get(i).getOrDefault(bitmask, -1d);
    }

    public static double distance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }
}
