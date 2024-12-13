package org.example.baekjoon.level.gold.five;

import java.io.*;
import java.util.*;

public class Bj15591 {

    static int[][] network;

    static Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        initNetwork(n);

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int video1 = Integer.parseInt(st.nextToken()) - 1;
            int video2 = Integer.parseInt(st.nextToken()) - 1;
            int usado = Integer.parseInt(st.nextToken());

            network[video1][video2] = usado;
            network[video2][video1] = usado;

            map.putIfAbsent(video1, new HashMap<>());
            map.putIfAbsent(video2, new HashMap<>());

            map.get(video1).put(video2, usado);
            map.get(video2).put(video1, usado);
        }

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int video = Integer.parseInt(st.nextToken()) - 1;

            bw.write(bfs(k, video) + "\n");
        }

        bw.flush();
    }

    static void initNetwork(int n) {
        network = new int[n][n];
    }

    static int bfs(int k, int from) {

        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{from, Integer.MAX_VALUE});
        boolean[] visited = new boolean[network.length];
        visited[from] = true;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int video = node[0];
            int usado = node[1];

            Map<Integer, Integer> nextMap = map.get(video);

            for (Integer nextVideo : nextMap.keySet()) {
                int nextUsado = Math.min(nextMap.get(nextVideo), usado);

                if (visited[nextVideo] || nextUsado < k) continue;
                network[from][nextVideo] = nextUsado;
                visited[nextVideo] = true;
                queue.add(new int[]{nextVideo, nextUsado});
                answer++;
            }
        }

        return answer;
    }
}
