//https://school.programmers.co.kr/learn/courses/30/lessons/133502
import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        
        for(int i : ingredient){
        	stack.add(i);
            if(i == 1 && isMake(stack)){
                answer++;
            }
        }
        return answer;
    }
    
    public boolean isMake(Stack<Integer> stack){
        if(stack.size() < 4) return false;
        if(stack.peek() != 1){
            return false;
        }
        stack.pop();
        if(stack.peek() != 3){
            stack.push(1);
            return false;
        }
        stack.pop();
        if(stack.peek() != 2){
            stack.push(3);
            stack.push(1);
            return false;
        }
        stack.pop();
        if(stack.peek() != 1){
            stack.push(2);
            stack.push(3);
            stack.push(1);
            return false;
        }
        stack.pop();
        return true;
    }
}