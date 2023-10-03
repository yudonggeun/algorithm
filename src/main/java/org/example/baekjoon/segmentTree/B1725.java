package org.example.baekjoon.segmentTree;
// https://www.acmicpc.net/problem/1725

import java.io.IOException;
import java.util.Scanner;

public class B1725 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] input = new int[size];

        for (int i = 0; i < size; i++) {
            input[i] = sc.nextInt();
        }

        allocate(size);
        initMin(1, 0, size - 1, input);
        System.out.println(maxArea(0, size - 1, input));
    }

    public static int maxArea(int left, int right, int[] array) {
        if (right < left) return 0;
        if (left == right) return array[left];

        int minIndex = minIndex(1, 0, array.length - 1, left, right, array);
        int area = (right - left + 1) * value(array, minIndex);
        area = Math.max(area, maxArea(left, minIndex - 1, array));
        area = Math.max(area, maxArea(minIndex + 1, right, array));
        return area;
    }

    static int[] min;

    public static void allocate(int size) {
        int h = (int) (Math.ceil(Math.log(size) / Math.log(2))) + 1;
        int length = (int) Math.pow(2, h);
        min = new int[length];
    }

    public static int initMin(int node, int start, int end, int[] array) {
        if (start == end) return min[node] = start;
        int mid = (start + end) / 2;

        int left = initMin(2 * node, start, mid, array);
        int right = initMin(2 * node + 1, mid + 1, end, array);

        if (array[left] > array[right]) min[node] = right;
        else min[node] = left;

        return min[node];
    }

    public static int minIndex(int node, int start, int end, int left, int right, int[] array) {
        if (end < left || start > right) return -1;
        if (left <= start && end <= right) return min[node];

        int mid = (start + end) / 2;

        int leftIndex = minIndex(2 * node, start, mid, left, right, array);
        int rightIndex = minIndex(2 * node + 1, mid + 1, end, left, right, array);

        return value(array, leftIndex) > value(array, rightIndex) ? rightIndex : leftIndex;
    }

    public static int value(int[] array, int index) {
        if (index < 0) return Integer.MAX_VALUE;
        return array[index];
    }
}