package org.example.baekjoon.spanningTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B17472 {

    static int[][] map;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        // 입력 받는다.
        var input = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(input.nextToken());
        int m = Integer.parseInt(input.nextToken());

        map = new int[n][];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        // 섬을 식별
        int lastIsland = 2;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                if (map[x][y] == 1) {
                    initIsland(x, y, lastIsland);
                    lastIsland++;
                }
            }
        }

        // 다리 식별
        Map<Integer, Map<Integer, Integer>> edgeMap = new HashMap<>();
        for (int x = 0; x < n; x++) {
            int from = 0;
            int length = 0;
            for (int y = 0; y < m; y++) {
                int id = map[x][y];
                if (id != 0) {
                    if (from == 0 || from == id) {
                        from = id;
                        length = 0;
                    } else {
                        int to = id;
                        addEdge(edgeMap, from, to, length);
                        addEdge(edgeMap, to, from, length);
                        from = to;
                        length = 0;
                    }
                } else {
                    length++;
                }
            }
        }

        for (int y = 0; y < m; y++) {
            int from = 0;
            int length = 0;
            for (int x = 0; x < n; x++) {
                int id = map[x][y];
                if (id != 0) {
                    if (from == 0 || from == id) {
                        from = id;
                        length = 0;
                    } else {
                        int to = id;
                        addEdge(edgeMap, from, to, length);
                        addEdge(edgeMap, to, from, length);
                        from = to;
                        length = 0;
                    }
                } else {
                    length++;
                }
            }
        }

        // mst 생성
        int sum = 0;
        boolean[] visit = new boolean[lastIsland];
        visit[2] = true;
        lastIsland--;
        var edges = new PriorityQueue<int[]>(Comparator.comparingInt(o -> o[1]));

        for (var entry : edgeMap.getOrDefault(2, new HashMap<>()).entrySet()) {
            int to = entry.getKey();
            int cost = entry.getValue();
            edges.add(new int[]{to, cost});
        }


        while (!edges.isEmpty()) {
            int[] edge = edges.poll();

            if (visit[edge[0]]) {
                continue;
            }

            sum += edge[1];
            lastIsland--;
            visit[edge[0]] = true;

            for (var entry : edgeMap.get(edge[0]).entrySet()) {
                int to = entry.getKey();
                int cost = entry.getValue();
                edges.add(new int[]{to, cost});
            }
        }
        // 결과 도출
        if (lastIsland == 2) System.out.println(sum);
        else System.out.println(-1);
    }

    private static void addEdge(Map<Integer, Map<Integer, Integer>> edgeMap, int from, int to, int length) {
        if (length < 2) return;
        edgeMap.putIfAbsent(from, new HashMap<>());
        var fromMap = edgeMap.get(from);
        fromMap.putIfAbsent(to, length);
        fromMap.put(to, Math.min(fromMap.get(to), length));
    }

    private static void initIsland(int x, int y, int id) {
        var queue = new LinkedList<int[]>();
        queue.add(new int[]{x, y});
        var visit = new boolean[map.length][map[0].length];

        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0];
            int c = point[1];
            map[r][c] = id;

            for (var m : move) {
                int nr = r + m[0];
                int nc = c + m[1];

                try {
                    if (visit[nr][nc] || map[nr][nc] != 1) continue;
                    queue.push(new int[]{nr, nc});
                    visit[nr][nc] = true;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    continue;
                }
            }
        }
    }

    static int[][] move = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
}
