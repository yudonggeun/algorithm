package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/154538
import java.util.Objects;
import java.util.PriorityQueue;

class ChangeNumber  {
    public int solution(int x, int y, int n) {

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(y, 0));

        while (!queue.isEmpty()){
            Node node = queue.poll();

            if(node.value == x) return node.cost;
            else if(node.value < x) continue;

            node.cost++;
            if(node.value % 3 == 0) queue.add(new Node(node.value / 3, node.cost));
            if(node.value % 2 == 0) queue.add(new Node(node.value / 2, node.cost));
            queue.add(new Node(node.value - n, node.cost));
        }
        return -1;
    }

    class Node implements Comparable<Node>{
        int value, cost;

        public Node(int value, int cost) {
            this.value = value;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}

//dp를 이용한 풀이
/*
import java.util.Arrays;

class Solution {

    public int solution(int x, int y, int n) {

        int[] numbers = new int[y * 3 + 1];
        Arrays.fill(numbers, Integer.MAX_VALUE);
        numbers[y] = 0;

        for (int i = y - 1; i >= x; i--) {
            numbers[i] = min(numbers[i + n], numbers[i * 2], numbers[i * 3]);
            if(numbers[i] != Integer.MAX_VALUE) numbers[i]++;
        }

        return numbers[x] == Integer.MAX_VALUE ? -1 : numbers[x];
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
*/