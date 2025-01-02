package org.example.baekjoon.level.gold.three;

import java.io.*;
import java.util.*;

public class Bj6087 {

    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        board = new char[h][];

        int sh = 0, sw = 0;
        for (int i = 0; i < h; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < w; j++) {
                if (board[i][j] == 'C') {
                    sh = i;
                    sw = j;
                }
            }
        }

        board[sh][sw] = '.';
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(o -> o.mirror));
        for (int direction = 0; direction < 4; direction++) {
            queue.add(new Node(direction, 0, sh, sw));
        }
        int[][] visited = new int[h][w];
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        visited[sh][sw] = 0;

        while (!queue.isEmpty()) {
            Node p = queue.poll();

            if (board[p.h][p.w] == 'C') {
                bw.write(p.mirror + "\n");
                break;
            }

            for (int type = 0; type < 3; type++) {
                Node next = p.getNext(type);
                if (isRange(next) && board[next.h][next.w] != '*') {
                    if (type == 0 && visited[next.h][next.w] >= next.mirror) {
                        visited[next.h][next.w] = next.mirror;
                        queue.add(next);
                    } else if (visited[next.h][next.w] > next.mirror) {
                        visited[next.h][next.w] = next.mirror;
                        queue.add(next);
                    }
                }
            }
        }
        bw.flush();
    }

    static boolean isRange(Node node) {
        return 0 <= node.h && node.h < board.length && 0 <= node.w && node.w < board[0].length;
    }

    static int LEFT = 1, UP = 2, RIGHT = 3, DOWN = 0;

    static class Node {
        int direction;
        int mirror;
        int h, w;

        Node(int direction, int mirror, int h, int w) {
            this.direction = direction;
            this.mirror = mirror;
            this.h = h;
            this.w = w;
        }

        public Node getNext(int type) {
            int direction = this.direction;
            int mirror = this.mirror;
            if (type == 1) {
                direction = (direction + 1) % 4;
                mirror++;
            } else if (type == 2) {
                direction = (direction + 3) % 4;
                mirror++;
            }

            Node node = new Node(direction, mirror, h, w);
            node.move();
            return node;
        }

        private void move() {
            if (direction == LEFT) {
                this.w--;
            } else if (direction == RIGHT) {
                this.w++;
            } else if (direction == UP) {
                this.h--;
            } else if (direction == DOWN) {
                this.h++;
            } else {
                System.out.println("error : " + direction);
            }
        }
    }
}
