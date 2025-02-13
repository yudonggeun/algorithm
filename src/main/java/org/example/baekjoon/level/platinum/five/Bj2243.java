package org.example.baekjoon.level.platinum.five;

import java.io.*;
import java.util.*;

public class Bj2243 {

    public static int MAX_SIZE = 1_000_000;
    public static int[] candies = new int[MAX_SIZE + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        create(1_000_000);

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 1) {
                int rank = Integer.parseInt(st.nextToken());
                int favor = find(rank);
                bw.write(favor + "\n");
                candies[favor]--;
                update(1, favor, candies[favor], 0, MAX_SIZE);
            } else {
                int favor = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                candies[favor] += count;
                update(1, favor, candies[favor], 0, MAX_SIZE);
            }
        }
        bw.flush();
    }

    // binary search
    public static int find(int rank) {
        int start = 0;
        int end = MAX_SIZE;
        while (start <= end) {
            int mid = (start + end) / 2;

            int sum = sum(1, 0, mid, 0, MAX_SIZE);
            if (rank <= sum) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    // segment
    static int[] segment;

    public static void create(int size) {
        int h = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
        segment = new int[1 << h];
    }

    public static int sum(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return 0;
        if (start <= left && right <= end) return segment[node];
        int mid = (left + right) / 2;
        return sum(2 * node, start, end, left, mid) +
                sum(2 * node + 1, start, end, mid + 1, right);
    }

    public static void update(int node, int index, int value, int left, int right) {
        if (index < left || right < index) return;
        if (left == right) {
            segment[node] = value;
            return;
        }
        int mid = (left + right) / 2;
        update(2 * node, index, value, left, mid);
        update(2 * node + 1, index, value, mid + 1, right);
        segment[node] = segment[2 * node] + segment[2 * node + 1];
    }

}
