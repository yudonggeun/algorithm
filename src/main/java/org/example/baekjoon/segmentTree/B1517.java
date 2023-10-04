package org.example.baekjoon.segmentTree;
//https://www.acmicpc.net/problem/1517

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1517 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] array = Arrays.stream(br.readLine()
                        .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < n; i++) indexList.add(i);
        Collections.sort(indexList, Comparator.comparingInt(o -> array[o]));

        allocate(array);
        long count = 0;
        for (int index : indexList) {
            count += sum(1, 0, n - 1, index + 1, n - 1);
            update(1, 0, n - 1, index, 1);
        }
        System.out.println(count);
    }

    static long[] tree;

    public static void allocate(int[] array) {
        int h = (int) Math.ceil(Math.log(array.length) / Math.log(2)) + 1;
        tree = new long[1 << h];
    }

    public static long sum(int node, int start, int end, int left, int right) {
        if (start > right || end < left) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(2 * node, start, mid, left, right) +
                sum(2 * node + 1, mid + 1, end, left, right);
    }

    public static void update(int node, int start, int end, int idx, int diff) {
        if (idx < start || end < idx) return;

        tree[node] += diff;
        if (start == end) return;

        int mid = (start + end) / 2;
        update(2 * node, start, mid, idx, diff);
        update(2 * node + 1, mid + 1, end, idx, diff);
    }
}