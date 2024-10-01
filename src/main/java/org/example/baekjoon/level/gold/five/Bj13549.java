package org.example.baekjoon.level.gold.five;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Bj13549 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int from = input[0];
        int to = input[1];
        int[] dp = new int[100_001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[from] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(o -> o.time));
        queue.add(new Node(from, 0));

        while (!queue.isEmpty()) {

            Node node = queue.poll();

            if (node.value == to) {
                System.out.println(node.time);
                return;
            }

            Node nextNode = new Node(node.value + 1, node.time + 1);
            if (isValid(nextNode.value) && dp[nextNode.value] > nextNode.time) {
                dp[nextNode.value] = nextNode.time;
                queue.add(nextNode);
            }
            nextNode = new Node(node.value - 1, node.time + 1);
            if (isValid(nextNode.value) && dp[nextNode.value] > nextNode.time) {
                dp[nextNode.value] = nextNode.time;
                queue.add(nextNode);
            }
            nextNode = new Node(node.value * 2, node.time);
            if (isValid(nextNode.value) && dp[nextNode.value] > nextNode.time) {
                dp[nextNode.value] = nextNode.time;
                queue.add(nextNode);
            }
        }
    }

    static class Node {
        public int value;
        public int time;

        public Node(int value, int time) {
            this.value = value;
            this.time = time;
        }
    }

    public static boolean isValid(int value) {
        return value >= 0 && value <= 100_000;
    }
}
