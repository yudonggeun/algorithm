package org.example.baekjoon.segmentTree;
// https://www.acmicpc.net/problem/2357

import java.io.IOException;
import java.util.Scanner;

public class B2357 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int caseSize = sc.nextInt();
        int[] input = new int[size];

        for (int i = 0; i < size; i++) {
            input[i] = sc.nextInt();
        }

        allocate(size);
        initMin(1, 0, size - 1, input);
        initMax(1, 0, size - 1, input);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < caseSize; i++) {
            int from = sc.nextInt() - 1;
            int to = sc.nextInt() - 1;

            int minValue = min(1, 0, size - 1, from, to);
            int maxValue = max(1, 0, size - 1, from, to);
            sb.append(minValue)
                    .append(" ")
                    .append(maxValue)
                    .append("\n");
        }

        System.out.println(sb);
    }

    static int[] min;
    static int[] max;

    public static void allocate(int size) {
        int h = (int) (Math.ceil(Math.log(size) / Math.log(2))) + 1;
        int length = (int) Math.pow(2, h);
        min = new int[length];
        max = new int[length];
    }

    public static int initMin(int node, int start, int end, int[] array) {
        if (start == end) return min[node] = array[start];
        int mid = (start + end) / 2;
        return min[node] = Math.min(
                initMin(2 * node, start, mid, array),
                initMin(2 * node + 1, mid + 1, end, array)
        );
    }

    public static int min(int node, int start, int end, int left, int right) {
        if (end < left || start > right) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return min[node];
        int mid = (start + end) / 2;
        return Math.min(min(2 * node, start, mid, left, right),
                min(2 * node + 1, mid + 1, end, left, right));
    }

    public static int initMax(int node, int start, int end, int[] array) {
        if (start == end) return max[node] = array[start];
        int mid = (start + end) / 2;
        return max[node] = Math.max(
                initMax(2 * node, start, mid, array),
                initMax(2 * node + 1, mid + 1, end, array)
        );
    }

    public static int max(int node, int start, int end, int left, int right) {
        if (end < left || start > right) return Integer.MIN_VALUE;
        if (left <= start && end <= right) return max[node];
        int mid = (start + end) / 2;
        return Math.max(max(2 * node, start, mid, left, right),
                max(2 * node + 1, mid + 1, end, left, right));
    }
}
