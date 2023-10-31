package org.example.baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B9345 {

    static int[] minTree;
    static int[] maxTree;
    static int[] books;

    public static void allocate(int size) {
        int treeSize = 1 << (int) (Math.ceil(Math.log(size) / Math.log(2)) + 1);
        minTree = new int[treeSize];
        maxTree = new int[treeSize];
        books = new int[size];
        Arrays.setAll(books, i -> i);
        initMax(1, 0, size - 1);
        initMin(1, 0, size - 1);
    }

    public static int initMax(int node, int start, int end) {
        if (start == end) {
            return maxTree[node] = start;
        }
        int mid = (start + end) / 2;
        return maxTree[node] = Math.max(
                initMax(2 * node, start, mid),
                initMax(2 * node + 1, mid + 1, end)
        );
    }

    public static int initMin(int node, int start, int end) {
        if (start == end) {
            return minTree[node] = start;
        }
        int mid = (start + end) / 2;
        return minTree[node] = Math.min(
                initMin(2 * node, start, mid),
                initMin(2 * node + 1, mid + 1, end)
        );
    }

    public static int min(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return minTree[node];
        int mid = (start + end) / 2;
        return Math.min(min(2 * node, start, mid, left, right),
                min(2 * node + 1, mid + 1, end, left, right));
    }

    public static int max(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return -1;
        if (left <= start && end <= right) return maxTree[node];
        int mid = (start + end) / 2;
        return Math.max(max(2 * node, start, mid, left, right),
                max(2 * node + 1, mid + 1, end, left, right));
    }

    public static void update(int node, int start, int end, int index, int value) {
        if (index < start || end < index) return;
        if (start == end) {
            minTree[node] = value;
            maxTree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        update(2 * node, start, mid, index, value);
        update(2 * node + 1, mid + 1, end, index, value);

        minTree[node] = Math.min(minTree[2 * node], minTree[2 * node + 1]);
        maxTree[node] = Math.max(maxTree[2 * node], maxTree[2 * node + 1]);
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            var input = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(input.nextToken());
            int repeat = Integer.parseInt(input.nextToken());
            allocate(size);

            for (int r = 0; r < repeat; r++) {
                input = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(input.nextToken());
                int from = Integer.parseInt(input.nextToken());
                int to = Integer.parseInt(input.nextToken());

                if (type == 0) {
                    int fromValue = books[from];
                    int toValue = books[to];
                    update(1, 0, size - 1, from, toValue);
                    update(1, 0, size - 1, to, fromValue);
                    books[from] = toValue;
                    books[to] = fromValue;
                } else {
                    int min = min(1, 0, size - 1, from, to);
                    int max = max(1, 0, size - 1, from, to);

                    if (min == from && max == to) System.out.println("YES");
                    else System.out.println("NO");
                }
            }
        }
    }
}
