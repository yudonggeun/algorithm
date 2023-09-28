package org.example.baekjoon.simulation.queue;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B13335 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int truckCount = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());
        int maxWeight = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Queue<Integer> trucks = new LinkedList<>();
        while (st.hasMoreTokens()) {
            trucks.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Node> bridge = new LinkedList<>();

        int sum = 0;
        int time = 0;

        while (!trucks.isEmpty()) {
            time++;
            while (!bridge.isEmpty() && bridge.peek().time <= time) {
                sum -= bridge.poll().weight;
            }
            if (trucks.peek() + sum <= maxWeight && bridge.size() <= length) {
                sum += trucks.peek();
                bridge.add(new Node(time + length, trucks.poll()));
            }
        }

        while (!bridge.isEmpty()) {
            time = bridge.poll().time;
        }
        System.out.println(time);
    }

    static class Node {
        int time;
        int weight;

        @Override
        public String toString() {
            return "{" +
                    weight +
                    '}';
        }

        public Node(int time, int weight) {
            this.time = time;
            this.weight = weight;
        }
    }

}
