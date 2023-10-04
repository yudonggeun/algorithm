package org.example.baekjoon.segmentTree;
//https://www.acmicpc.net/problem/10999

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10999 {
    long[] input;

    public static void main(String[] args) throws IOException {
        new B10999();
    }

    public B10999() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        input = new long[size];
        for (int i = 0; i < size; i++) {
            input[i] = Long.parseLong(br.readLine());
        }
        initTree(size);
        init(1, 0, size - 1, input);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            if (type == 1) update(1, 0, size - 1, from, to, Long.parseLong(st.nextToken()));
            else System.out.println(sum(1, 0, size - 1, from, to));
        }
    }

    long[] tree;
    long[] lazy;

    public void initTree(int size) {
        int h = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
        tree = new long[1 << h];
        lazy = new long[1 << h];
    }

    public long init(int node, int start, int end, long[] array) {
        if (start == end) return tree[node] = array[start];
        int mid = (start + end) / 2;
        return tree[node] = init(2 * node, start, mid, array) +
                init(2 * node + 1, mid + 1, end, array);
    }

    public void lazyUpdate(int node, int start, int end){
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if (start != end) {
                lazy[2 * node] += lazy[node];
                lazy[2 * node + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
    }

    public long sum(int node, int start, int end, int left, int right) {

        lazyUpdate(node, start, end);

        if (right < start || left > end) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(2 * node, start, mid, left, right) +
                sum(2 * node + 1, mid + 1, end, left, right);
    }

    public void update(int node, int start, int end, int left, int right, long diff) {

        lazyUpdate(node, start, end);
        if (right < start || end < left) return;

        if (left <= start && end <= right) {
            tree[node] += (end - start + 1) * diff;
            if (start != end) {
                lazy[2 * node] += diff;
                lazy[2 * node + 1] += diff;
            }
            return;
        }

        int mid = (start + end) / 2;
        update(2 * node, start, mid, left, right, diff);
        update(2 * node + 1, mid + 1, end, left, right, diff);

        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }
}
