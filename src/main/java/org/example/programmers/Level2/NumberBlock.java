package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/12923
class NumberBlock  {
    public int[] solution(long begin, long end) {
        int len = (int) (end - begin) + 1;
        int[] result = new int[len];

        for(int i = 0; i< result.length; i++){
            int num = i + (int) begin;
            result[i] = getNum(num);
        }
        if(begin == 1) result[0] = 0;
        return result;
    }

    public int getNum(int num){
        int result = 1;
        for(int i = 2; i<= Math.sqrt(num); i++){
            if(num % i == 0) {
                result = Math.max(result, i);
                if(num /i <= 10_000_000) return num /i;
            }
        }
        return result;
    }
}