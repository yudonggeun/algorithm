package org.example.baekjoon.spanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B4386 {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] parent = new int[n];
        int[] size = new int[n];
        Arrays.setAll(parent, i -> i);
        Arrays.fill(size, 1);

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            var input = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(input.nextToken());
            double y = Double.parseDouble(input.nextToken());
            nodes[i] = new Node(x, y, i);
        }

        var edges = new PriorityQueue<Edge>(Comparator.comparing(edge -> edge.cost));

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new Edge(nodes[i], nodes[j]));
            }
        }

        double sum = 0;
        int maxSize = 0;
        while (!edges.isEmpty() || maxSize < n) {
            var edge = edges.poll();
            if (isValid(edge, parent)) {
                sum += edge.cost;
                union(edge.from, edge.to, parent, size);
                maxSize = Math.max(maxSize, size[find(edge.to, parent)]);
            }
        }

        System.out.printf("%.2f%n", sum);
    }

    static boolean isValid(Edge edge, int[] parent) {
        int from = find(edge.from, parent);
        int to = find(edge.to, parent);
        return from != to;
    }

    static int find(int node, int[] parent) {
        if (parent[node] == node) return node;
        else return parent[node] = find(parent[node], parent);
    }

    static void union(int a, int b, int[] parent, int[] size) {
        int ra = find(a, parent);
        int rb = find(b, parent);

        if (ra == rb) return;
        parent[ra] = rb;
        size[rb] += size[ra];
    }

    static class Node {
        double x;
        double y;
        int index;

        public Node(double x, double y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    static class Edge {

        double cost;
        int from, to;

        public Edge(Node from, Node to) {
            this.from = from.index;
            this.to = to.index;
            double xdiff = from.x - to.x;
            double ydiff = from.y - to.y;
            cost = xdiff * xdiff + ydiff * ydiff;
            cost = Math.sqrt(cost);
        }
    }
}
