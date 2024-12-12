package org.example.baekjoon.level.gold.two;

import java.io.*;
import java.util.*;

public class Bj17780 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        nodeView = new Node[n][n];
        board = new int[n][];

        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());

            if (v == 1) v = RIGHT;
            else if (v == 2) v = LEFT;
            else if (v == 3) v = UP;
            else v = DOWN;

            Node node = new Node(r, c, v);
            nodeView[r][c] = node;
            nodes.add(node);
        }

        while (!finFlag) {
            move();
        }

        System.out.println(turn <= MAX_TRY ? turn : -1);
    }

    static final int MAX_TRY = 1000;

    static final int UP = 1, DOWN = -1, LEFT = 2, RIGHT = -2;
    static Map<Integer, int[]> direction;

    static {
        direction = new HashMap<>();
        direction.put(UP, new int[]{-1, 0});
        direction.put(DOWN, new int[]{1, 0});
        direction.put(LEFT, new int[]{0, -1});
        direction.put(RIGHT, new int[]{0, 1});
    }

    static final int RED = 1, BLUE = 2, WHITE = 0;

    static Node[][] nodeView;
    static List<Node> nodes = new ArrayList<>();
    static int[][] board;
    static boolean finFlag = false;
    static int turn = 0;

    static class Node {

        int r, c, v;
        Node next;

        public Node(int r, int c, int v) {
            this.r = r;
            this.c = c;
            this.v = v;
        }

        public Node reverseList() {
            Stack<Node> stack = new Stack<>();
            Node node = this;
            while (node != null) { // while(node.next != null) : 마지막 노드가 스택에 들어가지 않았음
                stack.push(node);
                Node next = node.next;
                node.next = null;
                node = next;
            }


            Node result = stack.peek();
            while (!stack.empty()) {
                node = stack.pop();
                node.next = stack.isEmpty() ? null : stack.peek();
            }
            return result;
        }

        public void reverseVector() {
            v *= -1;
        }

        int[] getNextPoint() {
            int[] move = direction.get(v);
            return new int[]{r + move[0], c + move[1]};
        }

        int count(int start) {
            start++;
            if (next == null) return start;
            else return next.count(start);
        }

        void updatePoint(int[] point) {
            this.r = point[0];
            this.c = point[1];
            if (next == null) return;
            next.updatePoint(point);
        }

        void addNode(Node node) {
            if (next == null) next = node;
            else next.addNode(node);
        }
    }

    static void move() {

        turn++;
        if (turn > MAX_TRY) {
            finFlag = true;
            return;
        }
        for (var node : nodes) {
            if (isBottom(node)) {
                var point = node.getNextPoint();

                int[] result = null;
                if (isRange(point[0], point[1])) {
                    switch (board[point[0]][point[1]]) {
                        case RED:
                            result = moveRed(node, point);
                            break;
                        case BLUE:
                            result = moveBlue(node, point);
                            break;
                        case WHITE:
                            result = moveWhite(node, point);
                            break;
                    }
                } else {
                    result = moveBlue(node, point);
                }

                // 검사 finFlag
                int count = nodeView[result[0]][result[1]].count(0);
                if (count >= 4) {
                    finFlag = true;
                    return;
                }
            }
        }

    }

    private static int[] moveBlue(Node node, int[] point) {
        int[] result = null;

        node.reverseVector();
        point = node.getNextPoint();

        if (isRange(point[0], point[1])) {
            switch (board[point[0]][point[1]]) {
                case RED:
                    result = moveRed(node, point);
                    break;
                case BLUE:
                    result = new int[]{node.r, node.c};
                    break;
                case WHITE:
                    result = moveWhite(node, point);
                    break;
            }
        } else {
            result = new int[]{node.r, node.c};
        }

        return result;
    }

    private static int[] moveRed(Node node, int[] point) {
        node = node.reverseList();
        return moveWhite(node, point);
    }

    private static int[] moveWhite(Node node, int[] to) {
        Node other = nodeView[to[0]][to[1]];
        nodeView[node.r][node.c] = null;
        if (other == null) nodeView[to[0]][to[1]] = node;
        else other.addNode(node);
        nodeView[to[0]][to[1]].updatePoint(to); // 실수 노드를 이동하고나서 nodeView와 이동한 노드들의 위치 상태를 갱신하지 않았음
        return to;
    }

    static boolean isBottom(Node node) {
        Node bottomNode = nodeView[node.r][node.c];
        return bottomNode.equals(node);
    }

    static boolean isRange(int r, int c) {
        return !(r < 0 || c < 0 || r >= board.length || c >= board[0].length);
    }
}
