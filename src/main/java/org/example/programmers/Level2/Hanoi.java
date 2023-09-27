package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/12946
import java.util.*;

class Hanoi  {
    public int[][] solution(int n) {
        move(1, 2, 3, n);
        int[][] result = new int[answer.size()][];
        for(int i = 0; i< result.length; i++){
            result[i] = answer.poll();
        }
        return result;
    }
    
    Queue<int[]> answer = new LinkedList<>();
    public void move(int start, int middle, int end, int n){
        if(n==1) {
            answer.add(new int[]{start, end});
            return;
        }
        move(start, end, middle, n-1);
        answer.add(new int[]{start, end});
        move(middle, start, end, n-1);
    }
}