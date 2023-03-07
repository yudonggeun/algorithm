//https://school.programmers.co.kr/learn/courses/30/lessons/154539
//풀이 1
import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers) {

        int[] bigIndex = new int[numbers.length];
        Arrays.setAll(bigIndex, i -> i);
        bigIndex[numbers.length - 1] = -1;

        for (int i = numbers.length - 2; i >= 0; i--) {
            int num = numbers[i];
            int index = i + 1;

            while (true) {
                if (index == -1 || num < numbers[index]) {
                    bigIndex[i] = index;
                    break;
                } else {
                    index = bigIndex[index];
                }
            }
        }
        Arrays.setAll(bigIndex, index -> {
            int value = bigIndex[index];
            if(value == -1) return -1;
            return numbers[value];
        });

        return bigIndex;
    }
}
//풀이 2
/*
import java.util.Stack;

class Solution {
    public int[] solution(int[] numbers) {

        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = numbers.length - 1; i > -1; i--) {
            int number = numbers[i];
            answer[i] = -1;
            while (!stack.isEmpty()){
                if(stack.peek() > number){
                    answer[i] = stack.peek();
                    break;
                } else stack.pop();
            }
            stack.push(number);
        }

        return answer;
    }
}
*/