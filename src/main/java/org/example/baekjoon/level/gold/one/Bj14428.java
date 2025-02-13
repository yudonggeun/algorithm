package org.example.baekjoon.level.gold.one;

import java.io.*;
import java.util.*;

public class Bj14428 {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = read();
        sequence = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sequence[i] = read();
        }
        sequence[n] = Integer.MAX_VALUE;
        init(n + 1);
        for (int i = 0; i <= n; i++) {
            init(1, i, 0, n);
        }

        int m = read();

        for (int a = 0; a < m; a++) {
            int type = read();

            if (type == 1) {
                int i = read() - 1;
                int v = read();
                sequence[i] = v;
                update(1, i, 0, n);
            } else {
                int i = read() - 1;
                int j = read() - 1;
                int index = minIndex(1, i, j, 0, n) + 1;
                bw.write(index + "\n");
            }
        }

        bw.flush();
    }

    public static int n;
    public static int[] sequence;
    public static int[] segment;

    public static void init(int size) {
        int h = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
        segment = new int[1 << h];
        Arrays.fill(segment, n);
    }

    public static int minIndex(int node, int start, int end, int left, int right) {
        if (end < left || start > right) return n;
        if (start <= left && right <= end) {
            return segment[node];
        }
        int mid = (left + right) / 2;
        int leftIndex = minIndex(2 * node, start, end, left, mid);
        int rightIndex = minIndex(2 * node + 1, start, end, mid + 1, right);

        if (aIsSmallerThanB(leftIndex, rightIndex)) {
            return leftIndex;
        } else {
            return rightIndex;
        }
    }

    public static boolean aIsSmallerThanB(int aIndex, int bIndex) {
        int a = sequence[aIndex];
        int b = sequence[bIndex];
        return a < b || (a == b && aIndex < bIndex);
    }

    public static void init(int node, int index, int left, int right) {
        if (index < left || index > right) return;

        if (aIsSmallerThanB(index, segment[node])) {
            segment[node] = index;
        }
        if (left < right) {
            int mid = (left + right) / 2;
            init(2 * node, index, left, mid);
            init(2 * node + 1, index, mid + 1, right);
        }
    }

    public static int update(int node, int index, int left, int right) {
        if (index < left || index > right) return segment[node];
        if (left == right) return segment[node];

        int mid = (left + right) / 2;
        int leftIndex = update(2 * node, index, left, mid);
        int rightIndex = update(2 * node + 1, index, mid + 1, right);
        if (aIsSmallerThanB(leftIndex, rightIndex)) {
            segment[node] = leftIndex;
        } else {
            segment[node] = rightIndex;
        }
        return segment[node];
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
