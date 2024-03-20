package org.example.baekjoon.implement;
// https://www.acmicpc.net/problem/12100

import java.util.*;
import java.io.*;

public class B12100 {

    private static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[][] board = new int[size][];

        for (int i = 0; i < size; i++) {
            board[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        var queue = new LinkedList<int[][]>();
        queue.add(board);

        for (int i = 0; i < 5; i++) {
            var nextQueue = new LinkedList<int[][]>();
            for (int[][] targetBoard : queue) {
                for (int direction : directions) {
                    int[][] clone = clone(targetBoard);
                    relocate(clone, direction);
                    nextQueue.add(clone);
                }
            }
            queue = nextQueue;
        }

        for (int[][] target : queue) {
            int max = findMax(target);
            maxValue = Math.max(max, maxValue);
        }
        System.out.println(maxValue);
    }

    private static int findMax(int[][] target) {
        int result = 0;
        for (int[] row : target) for (int e : row) result = Math.max(result, e);
        return result;
    }

    public static int[][] clone(int[][] board) {
        var copy = new int[board.length][];
        for (int i = 0; i < board.length; i++) copy[i] = board[i].clone();
        return copy;
    }

    /*
    left=-1, right=-2, top=1, bottom=2
     */
    static int[] directions = {-1, -2, 1, 2};

    /*
    합치기 & 재배열
     */
    public static void relocate(int[][] board, int direction) {
        int size = board.length;
        if (direction < 0) { // left & right
            for (int r = 0; r < size; r++) {
                var buffer = new LinkedList<Integer>();
                for (int c = 0; c < size; c++) {
                    if (board[r][c] != 0) {
                        buffer.offer(board[r][c]);
                    }
                    board[r][c] = 0;
                }

                if (direction == -1) { // left
                    for (int c = 0; c < size && !buffer.isEmpty(); c++) {
                        Integer element = buffer.pollFirst();
                        if (!buffer.isEmpty() && buffer.peekFirst().equals(element)) {
                            element *= 2;
                            buffer.pollFirst();
                        }
                        board[r][c] = element;
                    }
                } else { // right
                    for (int c = size - 1; c > -1 && !buffer.isEmpty(); c--) {
                        Integer element = buffer.pollLast();
                        if (!buffer.isEmpty() && buffer.peekLast().equals(element)) {
                            element *= 2;
                            buffer.pollLast();
                        }
                        board[r][c] = element;
                    }
                }
            }
        } else { // top & bottom
            for (int c = 0; c < size; c++) {
                var buffer = new LinkedList<Integer>();
                for (int r = 0; r < size; r++) {
                    if (board[r][c] != 0) {
                        buffer.offer(board[r][c]);
                    }
                    board[r][c] = 0;
                }

                if (direction == 1) { // top
                    for (int r = 0; r < size && !buffer.isEmpty(); r++) {
                        Integer element = buffer.pollFirst();
                        if (!buffer.isEmpty() && buffer.peekFirst().equals(element)) {
                            element *= 2;
                            buffer.pollFirst();
                        }
                        board[r][c] = element;
                    }
                } else {
                    // bottom
                    for (int r = size - 1; r > -1 && !buffer.isEmpty(); r--) {
                        Integer element = buffer.pollLast();
                        if (!buffer.isEmpty() && buffer.peekLast().equals(element)) {
                            element *= 2;
                            buffer.pollLast();
                        }
                        board[r][c] = element;
                    }
                }
            }
        }
    }
}
