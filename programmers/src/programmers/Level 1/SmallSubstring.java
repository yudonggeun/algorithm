//https://school.programmers.co.kr/learn/courses/30/lessons/147355
class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        for(int i = 0; i + p.length() <= t.length() ; i++){
            if(t.substring(i, i + p.length()).compareTo(p) <= 0) answer++;
        }
        return answer;
    }
}