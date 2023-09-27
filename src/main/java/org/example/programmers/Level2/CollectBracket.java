package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/12913
class CollectBracket  {
    int solution(int[][] land) {
        int answer = 0;
        for(int r = 1; r < land.length; r++){
            for(int c = 0; c < land[r].length; c++){
                int max = getMax(land[r-1], c);
                land[r][c] += max; 
                answer = Math.max(answer, land[r][c]);
            }
        }
        return answer;
    }
    
    public int getMax(int[] array, int not){
        int max = 0;
        for(int i = 0; i < array.length; i++){
            if(i==not) continue;
            max = Math.max(max, array[i]);
        }
        return max;
    }
}