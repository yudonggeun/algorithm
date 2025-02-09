package org.example.baekjoon.level.gold.one;

import java.io.*;
import java.util.*;

public class Bj9328 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCase; i++) {
            int[] size = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            char[][] board = new char[size[0]][size[1]];
            for (int j = 0; j < size[0]; j++) {
                board[j] = br.readLine().toCharArray();
            }
            char[] keys = br.readLine().toCharArray();
            if (keys[0] == '0') keys = new char[]{};
            bw.write(solution(board, keys) + "\n");
        }
        bw.flush();
    }

    public static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int solution(char[][] board, char[] keys) {
        int rowSize = board.length;
        int colSize = board[0].length;
        boolean[][] visited = new boolean[rowSize][colSize];
        Queue<int[]> queue = new LinkedList<>();

        int document = 0;

        HashMap<Character, List<int[]>> waitingQueue = new HashMap<>();
        for (char a = 'a'; a <= 'z'; a++) {
            waitingQueue.put(a, new ArrayList<>());
        }

        // 키 초기화
        Set<Character> myKeys = new HashSet<>();
        for (char key : keys) {
            myKeys.add(key);
        }

        // 초기 시작점 탐색
        for (int r = 0; r < rowSize; r++) {
            process(board, r, 0, myKeys, visited, waitingQueue, queue);
            process(board, r, colSize - 1, myKeys, visited, waitingQueue, queue);
        }
        for (int c = 0; c < colSize; c++) {
            process(board, 0, c, myKeys, visited, waitingQueue, queue);
            process(board, rowSize - 1, c, myKeys, visited, waitingQueue, queue);
        }

        // 갈 수 있는 모든 구간 탐색
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];

            if (isDocument(board[row][col])) {
                document++;
            }

            for (int[] m : move) {
                int nr = row + m[0];
                int nc = col + m[1];

                if (!isRange(nr, nc, rowSize, colSize)) continue;

                process(board, nr, nc, myKeys, visited, waitingQueue, queue);
            }
        }

        return document;
    }

    private static void process(char[][] board, int nr, int nc, Set<Character> myKeys, boolean[][] visited, HashMap<Character, List<int[]>> waitingQueue, Queue<int[]> queue) {
        if (visited[nr][nc]) return;
        char target = board[nr][nc];
        if (isWall(target)) return;
        if (isDoor(target)) {
            if (!myKeys.contains(toKey(target))) {
                visited[nr][nc] = true;
                waitingQueue.get(toKey(target)).add(new int[]{nr, nc});
                return;
            }
        }
        if (isKey(target)) {
            myKeys.add(target);
            for (int[] blockPoint : waitingQueue.get(target)) {
                visited[blockPoint[0]][blockPoint[1]] = true;
                queue.add(new int[]{blockPoint[0], blockPoint[1]});
            }
        }
        visited[nr][nc] = true;
        queue.add(new int[]{nr, nc});
    }

    public static char toKey(char door) {
        return Character.toLowerCase(door);
    }

    public static boolean isDocument(char c) {
        return c == '$';
    }

    public static boolean isWall(char c) {
        return c == '*';
    }

    public static boolean isDoor(char c) {
        return 'A' <= c && c <= 'Z';
    }

    public static boolean isKey(char c) {
        return 'a' <= c && c <= 'z';
    }

    public static boolean isRange(int r, int c, int sr, int sc) {
        return 0 <= r && r < sr && 0 <= c && c < sc;
    }
}
