//https://school.programmers.co.kr/learn/courses/30/lessons/160586
import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        
        Map<Character, Integer> map = new HashMap<>();
        
 		for(String key : keymap){
            for(int i = 0; i< key.length(); i++){
                map.putIfAbsent(key.charAt(i), i+1);
                map.replace(key.charAt(i), Math.min(map.get(key.charAt(i)), i+1));
            }
        }       
        int[] answer = new int[targets.length];
        
        for(int i = 0; i< answer.length; i++){
            int count = 0;
            for(int j = 0; targets[i].length() > j; j++){
                int add = map.getOrDefault(targets[i].charAt(j), -1);
                count += add;
                if(add == -1) {
                    count = -1;
                    break;
                }
            }
            answer[i] = count;
        }
        return answer;
    }
}