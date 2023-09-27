//https://school.programmers.co.kr/learn/courses/30/lessons/138477
import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        
        Queue<Integer> queue = new PriorityQueue<>();
        
        for(int i = 0; i < score.length; i++){
            int s = score[i];
            queue.add(s);
            if(queue.size() > k) queue.poll();
            score[i] = queue.peek();
        }
        
        return score;
    }
}