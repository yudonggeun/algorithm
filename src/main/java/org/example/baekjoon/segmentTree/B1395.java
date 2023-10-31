package org.example.baekjoon.segmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1395 {

    static int[] switches;
    static int[] tree;
    static boolean[] lazy;

    static void allocate(int size) {
        int h = (int) (Math.ceil(Math.log(size) / Math.log(2)) + 1);
        int treeSize = 1 << h;
        tree = new int[treeSize];
        lazy = new boolean[treeSize];
    }

    static int init(int node, int start, int end) {
        if (start == end) return tree[node] = switches[start];
        int mid = (start + end) / 2;
        return tree[node] = init(2 * node, start, mid) +
                init(2 * node + 1, mid + 1, end);
    }

    static int reverse(int value, int start, int end) {
        return end - start + 1 - value;
    }

    static void lazyUpdate(int node, int start, int end) {
        /*
        업데이트가 있음을 알릴 때 lazy[node] != 0 으로 해서 0으로 업데이트를 진행하는 상황에서 무시가 되어서 이상하게 동작함
         */
        if (lazy[node]) {
            tree[node] = reverse(tree[node], start, end);
            if (start != end) {
                /*
                부모의 노드라고 무조건 종속적인 관계가 아니다. 부모 노드가 lazy상태가 아닌데도 자식은 lazy 상태일 수 있기 때문이다.
                 */
                lazy[2 * node] = !lazy[2 * node];
                lazy[2 * node + 1] = !lazy[2 * node + 1];
            }
            lazy[node] = false;
        }
    }

    static void update(int node, int start, int end, int left, int right) {

        lazyUpdate(node, start, end);

        if (right < start || end < left) return;
        if (left <= start && end <= right) {
            tree[node] = reverse(tree[node], start, end);
            if (start != end) {
                lazy[2 * node] = !lazy[2 * node];
                lazy[2 * node + 1] = !lazy[2 * node + 1];
            }
            return;
        }

        int mid = (start + end) / 2;
        update(2 * node, start, mid, left, right);
        update(2 * node + 1, mid + 1, end, left, right);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    static int sum(int node, int start, int end, int left, int right) {
        lazyUpdate(node, start, end);

        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(2 * node, start, mid, left, right) +
                sum(2 * node + 1, mid + 1, end, left, right);
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var input = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        switches = new int[n];
        allocate(n);
        init(1, 0, n - 1);

        for (int i = 0; i < m; i++) {
            input = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(input.nextToken());
            int left = Integer.parseInt(input.nextToken()) - 1;
            int right = Integer.parseInt(input.nextToken()) - 1;

            if (type == 0) {// switch
                update(1, 0, n - 1, left, right);
            } else { // question
                int sum = sum(1, 0, n - 1, left, right);
                System.out.println(sum);
            }
        }
    }
}