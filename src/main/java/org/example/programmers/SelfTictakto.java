package org.example.programmers;

//https://school.programmers.co.kr/learn/courses/30/lessons/160585
class Solution {

    String [] board;
    public int solution(String[] board) {

        this.board = board;

        int oCount = 0, xCount = 0;
        for (String string : board) {
            for (char c : string.toCharArray()) {
                if (c == 'O') oCount++;
                else if (c == 'X') xCount++;
            }
        }

        int diff = oCount - xCount;
        if(diff < 0 || diff > 1) return 0;

        boolean oWin = whoAreWin('O');
        boolean xWin = whoAreWin('X');

        if(oWin && xWin) return 0;
        if(oWin && diff == 0) return 0;
        if(xWin && diff == 1) return 0;
        return 1;
    }

    int[][] directions = new int[][]{
            {0, 1},//가로
            {1, 0},//세로
            {1, 1},//대각
            {1, -1}
    };

    public boolean whoAreWin(char who) {
        for (int i = 0; i < 3; i++) {
            if (isWinner(new int[]{i, 0}, directions[0], who)) return true;
            if (isWinner(new int[]{0, i}, directions[1], who)) return true;
            if (isWinner(new int[]{0, 0}, directions[2], who)) return true;
            if (isWinner(new int[]{0, 2}, directions[3], who)) return true;
        }
        return false;
    }

    public boolean isWinner(int[] point, int[] direction, char who) {
        if (who == '.') return false;
        for (int i = 0; i < 3; i++) {
            if (board[point[0]].charAt(point[1]) != who) return false;
            point[0] += direction[0];
            point[1] += direction[1];
        }
        return true;
    }
}