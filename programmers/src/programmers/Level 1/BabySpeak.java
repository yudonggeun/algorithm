//https://school.programmers.co.kr/learn/courses/30/lessons/133499?language=java
import java.util.regex.Pattern;

class Solution {

   public int solution(String[] babbling) {

        Pattern duplication = Pattern.compile(".*(aya|ye|woo|ma)\\1.*");
        Pattern match = Pattern.compile("^(aya|ye|woo|ma)+$");
        int answer = 0;
        for(int i = 0; i< babbling.length; i++){
            if(!match.matcher(babbling[i]).matches()) continue;
            if(duplication.matcher(babbling[i]).matches()) continue;
            answer++;
        }
        return answer;
    }
}