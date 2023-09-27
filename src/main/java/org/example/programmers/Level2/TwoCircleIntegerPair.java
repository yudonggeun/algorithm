package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/181187#
class TwoCircleIntegerPair {
    public long solution(int r1, int r2) {
        return getCount(r2, false) - getCount(r1, true);
    }

    public long getCount(int r, boolean inner){
        long count = 0;
        for(int x = -r; x <= r; x++){
            count += getRange(r, x, inner);
        }
        return count;
    }

    public long getRange(long r, long x, boolean inner){
        double y = Math.sqrt(r*r - x*x);
        long longY = (long) y;
        if(inner && y == 0.0) return 0;
        if(inner && (double) longY == y) y--;
        return 2 * (long) y + 1; 
    }
}