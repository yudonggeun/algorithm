package org.example.programmers.level4;

import java.util.*;

class BlindDateForTube{
    int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[] solution(int m, int n, int s, int[][] time_map) {

        long[][] visit = new long[m][n];
        for (long[] v : visit) { Arrays.fill(v, Long.MAX_VALUE); }

        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.count == o2.count) {
                return Long.compare(o1.time, o2.time);
            } else {
                return o1.count - o2.count;
            }
        });
        queue.add(new Node(0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (visit[node.r][node.c] <= node.time) continue;
            visit[node.r][node.c] = node.time;

            if (node.r == m - 1 && node.c == n - 1) {
                return new int[]{node.count, (int) node.time};
            }

            for (int[] nm : move) {
                int nr = node.r + nm[0];
                int nc = node.c + nm[1];
                long nextTime = node.time + time_map[node.r][node.c];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || visit[nr][nc] <= nextTime) continue;
                if (time_map[nr][nc] == -1) continue;
                if (nextTime > s) continue;
                queue.add(new Node(nr, nc, node.count + 1, nextTime));
            }
        }

        return null;
    }

    class Node {
        int r, c, count;
        long time;

        public Node(int r, int c, int count, long time) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.time = time;
        }
    }
}