package org.example.baekjoon.spanningTree;

import java.io.*;
import java.util.*;

public class B2465 {

    static int[] tree, heights;

    static int init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = 1;
        } else {
            int mid = (start + end) / 2;
            return tree[node] = init(2 * node, start, mid) + init(2 * node + 1, mid + 1, end);
        }
    }

    static Stack<Integer> stack = new Stack<>();

    static void remove(int node, int start, int end, int count) {
        tree[node]--;
        if (start == end) {
            stack.push(heights[start]);
            return;
        }
        int mid = (start + end) / 2;
        int leftCount = tree[2 * node];
        if (count <= leftCount) {
            remove(2 * node, start, mid, count);
        } else {
            remove(2 * node + 1, mid + 1, end, count - leftCount);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // read size
        int size = Integer.parseInt(br.readLine());

        // set variable and init
        heights = new int[size];
        int h = (int) Math.ceil(Math.log(size) / Math.log(2));
        tree = new int[(1 << (1 + h)) + 2];
        init(1, 0, size);

        // read height
        for (int i = 0; i < size; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        // read position
        int[] position = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        // sort
        Arrays.sort(heights);

        for (int i = size - 1; i > -1; i--) remove(1, 0, size, position[i] + 1);

        while (!stack.isEmpty()) bw.write(stack.pop() + "\n");
        bw.flush();
    }
}
