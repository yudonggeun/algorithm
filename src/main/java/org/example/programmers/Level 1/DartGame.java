//https://school.programmers.co.kr/learn/courses/30/lessons/17682
import java.util.*;

class Solution {
    public int solution(String dartResult) {
        
        String [] numbers = dartResult.split("[SDT*#]");
        String [] options = dartResult.split("[0-9]");
        
        Queue<Integer> numQueue = new LinkedList<>();
        Queue<String> optionQueue = new LinkedList<>();
        
        for(String s : numbers){
            if("".equals(s)) continue;
            numQueue.add(Integer.parseInt(s));
        }
        
        for(String s : options){
            if("".equals(s)) continue;
            optionQueue.add(s);
        }
        
        int[] score = new int[numQueue.size()];
        Arrays.setAll(score, i -> numQueue.poll());
        
        for(int i = 0; i< score.length; i++){
            String option = optionQueue.poll();
            
            char bonus = option.charAt(0);
            if(bonus == 'D') score[i] = score[i] *  score[i]  ; 
            else if(bonus == 'T') score[i] = score[i] * score[i] * score[i];

            if(option.length() == 2){
                char op = option.charAt(1);
                if(op == '*'){
                    score[i] *=2;
                    if(i != 0) score[i-1] *=2;
                } else {
                    score[i] *= -1;
                }
            }
        }
        return Arrays.stream(score).sum();
    }
}