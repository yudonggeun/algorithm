//https://school.programmers.co.kr/learn/courses/30/lessons/181188
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> {
            return o1[0] - o2[0];
        });
        
        int count = 0;
        
        for(int i = 0; i< targets.length -1; i++){
            if(targets[i][1] <= targets[i+1][0]){
                count++;
            } else {
         		targets[i+1][1] = Math.min(targets[i+1][1], targets[i][1]);       
            }
        }
        return count + 1;
    }
}