// https://school.programmers.co.kr/learn/courses/30/lessons/1838
package org.example.programmers.Level3;
import java.util.*;

class Trainer {
    Map<Integer, Set<Integer>> visit = new HashMap<>();
    int[][] board;
    
    public int solution(int n, int m, int[][] timetable) {
        board = new int[n][n];
        for(int[] b : board) Arrays.fill(b, Integer.MAX_VALUE -1);
        for(int i = 0; i < n; i++) visit.put(i, new HashSet<>());
        // 1. 최대 중복 회원 수 구하기
        int[] times = new int[1321];
        for(int[] t : timetable){
            int from = t[0];
            int to = t[1] + 1;
            times[from]++;
            times[to]--;
        }
        for(int i = 1; i < times.length; i++){
            times[i] += times[i-1];
        }
        int maxPeople = 0;
        for(int t : times){
            maxPeople = Math.max(t, maxPeople);
        }
        if(maxPeople < 2) return 0;
        // 2. 최대 중복시 최소 값 구하기
        int start = 1;
        int end = 2 * (n - 1);
        while(start <= end){
            int mid = (start + end)/ 2;
            if(isPossible(maxPeople, mid)){
                start = mid + 1;
            } else {
                end = mid -1;
            }
        }
        if(end == -1) return 0;
        return end;
    }
    
    class Point{
        int r, c;
        
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
        
        public int distance(int r, int c){
            return Math.abs(this.r - r) + Math.abs(this.c -c);
        }
    }
    
    public boolean isPossible(int peopleCount, int minPath) {
        for(int sc = 0; sc < board.length; sc++){
        	List<Point> points = new ArrayList<>();
            for(int r = 0; r < board.length; r++){
                for(int c = 0; c < board.length; c++){
                    if(r == 0 && c < sc) continue;
                    final int tr = r;
                    final int tc = c;
                    Optional<Point> any = points.stream()
                        .filter(p -> p.distance(tr, tc) < minPath)
                        .findAny();

                    if(any.isEmpty()){
                        points.add(new Point(r, c));
                        if(points.size() >= peopleCount) return true;
                    }
                }
            }
        }
        return false;
    }
}