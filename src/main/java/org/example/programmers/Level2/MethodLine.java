package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/12936
import java.util.*;

class MethodLine  {
    public int[] solution(int n, long k) {
        List<Integer> answer = new ArrayList<>(n);
        List<Integer> list = new ArrayList<>();
      
        k--;
        long nf = 1;
        for(int i = 1; i <= n; i++){
            nf *= i;
            list.add(i);
        }
        
        for(int dif = n; dif >= 1; dif--){
            nf /= dif;
            int index = (int) (k / nf);
            if(index < 0) {
                answer.add(list.get(0));
                break;
            }
            answer.add(list.remove(index));
            k = k % nf;
        }
        
        int[] result = new int[answer.size()];
        for(int i = 0; i< result.length; i++){
            result[i] = answer.get(i);
        }
        return result;
    }
}