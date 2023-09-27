//https://school.programmers.co.kr/learn/courses/30/lessons/118666
import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> result = new HashMap<>();
        String types = "RTCFJMAN";
        for(char type : types.toCharArray()){
            result.put(type, 0);
        }
        
        for(int i = 0; i< survey.length; i++){
            String type = survey[i];
            int amount = Math.abs(add[choices[i]]);
            if(add[choices[i]] < 0){
                result.computeIfPresent(type.charAt(1), (k, v) -> v + amount);
            } else {
                result.computeIfPresent(type.charAt(0), (k, v) -> v + amount);
            }
        }
        
        String answer = "";
        answer += result.get('R') < result.get('T') ? 'T' : 'R';
        answer += result.get('C') < result.get('F') ? 'F' : 'C';
        answer += result.get('J') < result.get('M') ? 'M' : 'J';
        answer += result.get('A') < result.get('N') ? 'N' : 'A';
        return answer;
    }
    
    int[] add = {0, 3, 2, 1, 0, -1, -2, -3};
}