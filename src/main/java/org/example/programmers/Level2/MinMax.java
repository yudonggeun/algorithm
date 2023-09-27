package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/12939
class MinMax  {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(String input : s.split(" ")){
            int num = Integer.parseInt(input);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        sb.append(min).append(" ").append(max);
        return sb.toString();
    }
}