package org.example.programmers.Level1;

//https://school.programmers.co.kr/learn/courses/30/lessons/140108
class StringDivide {
    public int solution(String s) {
        int answer = 0;
        
        Character target = null;
        int xCount = 0;
        int notXCount = 0;
        for(int i = 0; i< s.length(); i++){
            if(target == null) {
                target = s.charAt(i);
                xCount = 1;
                notXCount = 0;
                answer++;
                continue;
            }
            if(target == s.charAt(i)) xCount++;
            else notXCount++;
            
            if(xCount == notXCount){
                target = null;
            }
        }
        return answer;
    }
}