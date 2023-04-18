//https://school.programmers.co.kr/learn/courses/30/lessons/67256
import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {

        initSet();
        StringBuilder answer = new StringBuilder();
        Thumb left = new Thumb("left", hand), right = new Thumb("right", hand);
        
        for(int number : numbers){
			if(left.beUsedThan(right, number)){
                answer.append("L");
                left.point = number;
            } else {
                answer.append("R");
                right.point = number;
            }
        } 
        
        return answer.toString();
    }

    int[][] path = {
        {0, 4, 3, 4, 3, 2, 3, 2, 1, 2},//0
        {4, 0, 1, 2, 1, 2, 3, 2, 3, 4},//1
        {3, 1, 0, 1, 2, 1, 2, 3, 2, 3},//2
        {4, 2, 1, 0, 3, 2, 1, 4, 3, 2},//3
        {3, 1, 2, 3, 0, 1, 2, 1, 2, 3},//4
        {2, 2, 1, 2, 1, 0, 1, 2, 1, 2},//5
        {3, 3, 2, 1, 2, 1, 0, 3, 2, 1},//6
        {2, 2, 3, 4, 1, 2, 3, 0, 1, 2},//7
        {1, 3, 2, 3, 2, 1, 2, 1, 0, 1},//8
        {2, 4, 3, 2, 3, 2, 1, 2, 1, 0},//9
        {1, 3, 4, 5, 2, 3, 4, 1, 2, 3},//*
        {1, 5, 4, 3, 4, 3, 2, 3, 2, 1},//#
    };
    
    Set<Integer> leftSet = new HashSet<>();
    Set<Integer> rightSet = new HashSet<>();
    
    public void initSet(){
        leftSet.add(1);
        leftSet.add(4);
        leftSet.add(7);
        
        rightSet.add(3);
        rightSet.add(6);
        rightSet.add(9);
    }

    class Thumb{
        boolean priority;
        int point;//*=10 $=11
        boolean isLeft;
        
        @Override
        public String toString(){
            return point + "";
        }

        Thumb(String direction, String hand){
            isLeft = direction.equals("left");
            point = isLeft ? 10 : 11;
            priority = direction.equals(hand);
        }

        public boolean beUsedThan(Thumb other, int target){
            if(leftSet.contains(target)) return isLeft;
            if(rightSet.contains(target)) return !isLeft;
            if(path[point][target] < path[other.point][target]){
                return true;
            } else if(path[point][target] > path[other.point][target]){
                return false;
            } else {
                return priority;
            }
        }
    }
}