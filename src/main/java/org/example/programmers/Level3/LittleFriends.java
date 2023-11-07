package org.example.programmers.Level3;
// https://school.programmers.co.kr/learn/courses/30/lessons/1836

import java.util.*;

public class LittleFriends {
    public String solution(int m, int n, String[] board) {
        Map<Character, List<int[]>> map = new TreeMap<>();

        char[][] charBoard = new char[board.length][];
        for (int x = 0; x < board.length; x++) {
            charBoard[x] = board[x].toCharArray();
            for (int y = 0; y < charBoard[x].length; y++) {
                char element = charBoard[x][y];
                if (element == '.' || element == '*') continue;
                map.putIfAbsent(element, new ArrayList<>(2));
                map.get(element).add(new int[]{x, y});
            }
        }

        StringBuilder sb = new StringBuilder();
        List<Character> deleteCandidates = new LinkedList<>();

        while (!map.isEmpty()) {
            for (Character c : map.keySet()) {
                var list = map.get(c);
                int[] from = list.get(0);
                int[] to = list.get(1);
                if (isDelete(from, to, charBoard)) {
                    charBoard[from[0]][from[1]] = '.';
                    charBoard[to[0]][to[1]] = '.';
                    sb.append(c);
                    deleteCandidates.add(c);
                    break;
                }
            }
            if(deleteCandidates.isEmpty() && !map.isEmpty()){
                return "IMPOSSIBLE";
            }

            for (Character delete : deleteCandidates) {
                map.remove(delete);
            }
            deleteCandidates.clear();
        }

        return sb.toString();
    }

    public boolean isDelete(int[] from, int[] to, char[][] board) {
        char target = board[from[0]][from[1]];
        int sX = Math.min(from[0], to[0]);
        int eX = Math.max(from[0], to[0]);
        int sY = Math.min(from[1], to[1]);
        int eY = Math.max(from[1], to[1]);

        // case 1
        boolean result1 = true;
        boolean result2 = true;
        for (int x = sX; x <= eX; x++) {
            char case1 = board[x][from[1]];
            char case2 = board[x][to[1]];
            if (case1 != '.' && case1 != target) {
                result1 = false;
            }
            if (case2 != '.' && case2 != target) {
                result2 = false;
            }
        }

        for (int y = sY; y <= eY; y++) {
            char case1 = board[to[0]][y];
            char case2 = board[from[0]][y];
            if (case1 != '.' && case1 != target) {
                result1 = false;
            }
            if (case2 != '.' && case2 != target) {
                result2 = false;
            }
        }
        return result1 || result2;
    }}