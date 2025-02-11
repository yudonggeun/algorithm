package org.example.baekjoon.level.gold.two;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Bj13334 {
    public static class Line {
        int from, to;

        public Line(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        List<Line> lines = new ArrayList<>();
        Set<Integer> values = new HashSet<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (from > to) {
                int tem = from;
                from = to;
                to = tem;
            }
            values.add(from);
            values.add(to);
            lines.add(new Line(from, to));
        }
        int size = Integer.parseInt(br.readLine());

        sortedValues = values.stream().sorted().collect(Collectors.toList());

        createSegment(sortedValues.size());
        for (Line line : lines) {
            int to = line.to;
            update(1, index(to), 0, sortedValues.size() - 1, 1);
        }

        Collections.sort(lines, Comparator.comparingInt(l -> l.from));

        int right = sortedValues.size() - 1;
        int answer = 0;
        for (Line line : lines) {
            int from = line.from;
            int sum = sum(index(from), index(from + size), 0, right, 1);
            answer = Math.max(answer, sum);
            update(1, index(line.to), 0, right, -1);
        }

        System.out.println(answer);
    }

    static List<Integer> sortedValues;

    public static int index(int value) {
        int start = 0;
        int end = sortedValues.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int target = sortedValues.get(mid);

            if (target == value) {
                return mid;
            } else if (target > value) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return end;
    }

    public static int[] segment;

    public static void createSegment(int size) {
        int h = (int) Math.ceil(Math.log(size) / Math.log(2)) + 1;
        segment = new int[1 << h];
    }

    public static int sum(int start, int end, int left, int right, int node) {
        if (right < start || end < left) return 0;
        if (start <= left && right <= end) {
            return segment[node];
        } else {
            int mid = (left + right) / 2;
            return sum(start, end, left, mid, node * 2) +
                    sum(start, end, mid + 1, right, node * 2 + 1);
        }
    }

    public static void update(int node, int index, int left, int right, int value) {
        if (left <= index && index <= right) {
            segment[node] += value;
            if (left != right) {
                int mid = (left + right) / 2;
                update(node * 2, index, left, mid, value);
                update(node * 2 + 1, index, mid + 1, right, value);
            }
        }
    }
}
