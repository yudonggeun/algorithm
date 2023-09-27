package org.example.programmers.Level1;//https://school.programmers.co.kr/learn/courses/30/lessons/68644
import java.util.*;

class SelectNumbersAndAdd {
    public int[] solution(int[] numbers) {
        Set<Integer> result = new TreeSet<>();
        
        for(int i = 0; i < numbers.length; i++){
            for(int j = i+1; j < numbers.length; j++){
                result.add(numbers[i] + numbers[j]);
            }
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }
}