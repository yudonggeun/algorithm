package org.example.programmers.Level1;

//https://school.programmers.co.kr/learn/courses/30/lessons/136798
class WeaponOfNight {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        for(int i = 1; i <= number; i++){
            int count = aliquotCount(i);
            if(count > limit) count = power;
            answer += count;
        }
        return answer;
    }

    public int aliquotCount(int number){
        int count = 0;
        for(int i = 1; i <= Math.sqrt(number); i++){
            if(number % i == 0)
                count += 2;
        }
        if(Math.sqrt(number) % 1 == 0){
            count--;
        }

        return count;
    }
}