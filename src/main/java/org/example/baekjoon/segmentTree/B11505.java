package org.example.baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B11505 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        var input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());
        int k = Integer.parseInt(input.nextToken());

        numbers = new long[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }
        init();
        init(1, 0, numbers.length - 1);

        for (int i = 0; i < m + k; i++) {
            input = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int c = Integer.parseInt(input.nextToken());

            if (type == 1) {
                update(1, 0, numbers.length - 1, b - 1, c);
            } else {
                long multiply = multiply(1, 0, numbers.length - 1, b - 1, c - 1);
                System.out.println(multiply);
            }
        }
    }

    static long[] numbers;
    static long[] tree;

    static int MAX = 1_000_000_007;

    public static void init() {
        int h = (int) (Math.ceil(Math.log(numbers.length) / Math.log(2)));
        int size = 1 << (h + 1);
        tree = new long[size];
        Arrays.fill(tree, 1);
    }

    public static long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = numbers[start];
        }

        int mid = (start + end) / 2;
        tree[node] = init(2 * node, start, mid) * init(2 * node + 1, mid + 1, end) % MAX;
        return tree[node];
    }

    public static long update(int node, int start, int end, int index, long next) {
        // 실수한 부분 or 사용을 하지 않고 and를 사용해서 시간 초과가 발생함
        if (index < start || end < index) return tree[node];
        if (start == end) {
            numbers[index] = next;
            return tree[node] = next;
        }

        int mid = (start + end) / 2;
        return tree[node] = update(2 * node, start, mid, index, next)
                * update(2 * node + 1, mid + 1, end, index, next) % MAX;
    }

    public static long multiply(int node, int start, int end, int left, int right) {
        if (end < left || right < start) {
            return 1;
        }

        if (left <= start && end <= right) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return multiply(2 * node, start, mid, left, right) *
                multiply(2 * node + 1, mid + 1, end, left, right) % MAX;
    }
}
