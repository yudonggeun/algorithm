package org.example.programmers.Level1;//https://school.programmers.co.kr/learn/courses/30/lessons/86491
import java.util.*;

class MinSquare{
    public int solution(int[][] sizes) {
        
        for(int[] size : sizes)
            Arrays.sort(size);
        
        int w = 0, h = 0;
        
        for(int[] size : sizes){
            w = Math.max(w, size[0]);
            h = Math.max(h, size[1]);
        }
        
        return w * h;
    }
}