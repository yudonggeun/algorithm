package org.example.baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2042 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        n += Integer.parseInt(st.nextToken());

        long[] array = new long[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = Long.parseLong(br.readLine());
        }

        treeInit(array.length);
        init(array, 1, 0, size - 1);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                long value = Long.parseLong(st.nextToken());
                update(1, 0, size - 1, index, value - array[index]);
                array[index] = value;

            } else {
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                long sum = sum(1, 0, size - 1, from, to);
                System.out.println(sum);
            }
        }
    }

    static long[] tree;
    static int treeSize;

    public static void treeInit(int size) {
        int h = (int) Math.ceil(Math.log(size) / Math.log(2));
        treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];
    }

    public static long init(long[] array, int node, int start, int end) {
        if (start == end) return tree[node] = array[start];

        int mid = (start + end) / 2;
        return tree[node] = init(array, node * 2, start, mid) +
                init(array, node * 2 + 1, mid + 1, end);
    }

    private static long sum(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return 0;

        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right) +
                sum(node * 2 + 1, mid + 1, end, left, right);
    }

    private static void update(int node, int start, int end, int index, long diff) {
        if (index < start || end < index) return;

        tree[node] += diff;

        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, index, diff);
            update(node * 2 + 1, mid + 1, end, index, diff);
        }
    }
}