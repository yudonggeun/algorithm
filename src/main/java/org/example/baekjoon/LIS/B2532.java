package org.example.baekjoon.LIS;
// https://acmicpc.net/problem/2532


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B2532 {

    public static class Node {
        public Integer from, to;

        public Node(Integer from, Integer to) {
            this.from = from;
            this.to = to;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Map<Integer, Map<Integer, Boolean>> visit = new HashMap<>();
        Map<Integer, Boolean> emptyMap = new HashMap<>();

        Queue<Node> data = new PriorityQueue<>((o1, o2) -> {
            int diff = Integer.compare(o2.from, o1.from);
            if (diff == 0) return Integer.compare(o1.to, o2.to);
            return diff;
        });

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();

            Integer left = Integer.parseInt(st.nextToken());
            Integer right = Integer.parseInt(st.nextToken());

            if (visit.getOrDefault(left, emptyMap).get(right) == null) {
                visit.putIfAbsent(left, new HashMap<>());
                visit.get(left).put(right, true);
                data.add(new Node(left, right));
            }
        }

        List<Integer> lis = new ArrayList<>(visit.size());

        while (!data.isEmpty()) {
            Node node = data.poll();
            Integer value = node.to;

            int point = insertPoint(lis, value);
            if (point == lis.size()) lis.add(value);
            else lis.set(point, value);
        }

        System.out.println(lis.size());
    }


    public static int insertPoint(List<Integer> lis, Integer value) {
        int point = Collections.binarySearch(lis, value);
        if (point < 0) point = Math.abs(point + 1);
        else {
            point = Collections.binarySearch(lis, value + 1);
            point = point < 0 ? Math.abs(point + 1) : point;
        }
        return point;
    }
}
