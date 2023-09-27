package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/17684?language=java
import java.util.*;

class Compress  {
    public int[] solution(String msg) {
        List<Integer> answer = new LinkedList<>(); 
        init();
        while(msg.length() > 0){
            int end = nextEnd(msg);
            String key = msg.substring(0, end);
            answer.add(dict.get(key));
            
            if(key.length() == msg.length()){
                break;
            }
            dict.put(msg.substring(0, end+1), dictIndex++);
            msg = msg.substring(end);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    int dictIndex = 27;
    Map<String, Integer> dict = new HashMap<>();
    
    public void init(){
        int index = 1;
        for(char c = 'A'; c <= 'Z' ; c++){
            dict.put(c+"", index++);
        }
    }
    
    public int nextEnd(String s){
        int end = s.length();
        for(; end > 0; end--){
            String next = s.substring(0, end);
            if(dict.keySet().contains(next)){
                return end;
            }
        }
        return 1;
    }
}