//https://school.programmers.co.kr/learn/courses/30/lessons/142086
import java.util.*;

class Solution {
    public int[] solution(String s) {
        List<Integer> answer = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();
        
        char[] array = s.toCharArray();
        
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int index = map.getOrDefault(c, -1);
            if(index == -1){
        		answer.add(-1);        
            } else {
                answer.add(i - index);
            }
            map.put(c, i);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}