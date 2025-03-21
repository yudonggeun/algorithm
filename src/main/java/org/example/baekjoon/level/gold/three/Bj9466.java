package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj9466 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(e -> Integer.parseInt(e) - 1).toArray();
            bw.write(solution(n, input) + "\n");
        }
        bw.flush();
    }

    public static int solution(int n, int[] input) {

        int[] visited = new int[n];
        boolean[] check = new boolean[n];
        for (int num = 0; num < n; num++) {
            int t = num;
            Set<Integer> nodes = new HashSet<>();
            while (!check[t] && visited[t] != 2) {
                visited[t]++;
                nodes.add(t);
                t = input[t];
            }
            for (Integer e : nodes) {
                check[e] = true;
            }
        }

        return Arrays.stream(visited).filter(v -> v == 1).sum();
    }
}
