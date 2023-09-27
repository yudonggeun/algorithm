package org.example.programmers.Level1;

//https://school.programmers.co.kr/learn/courses/30/lessons/134240
class FoodFight{
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < food.length; i++){
            for(; food[i] > 1; food[i]-=2){
                sb.append(i);
            }
        }
        
        String answer = sb.toString() + "0";
        sb.reverse();
        answer += sb.toString();
        return answer;
    }
}