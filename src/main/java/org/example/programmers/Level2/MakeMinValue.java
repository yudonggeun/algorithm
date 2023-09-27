package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/12941
import java.util.*;

class MakeMinValue
{
    public int solution(int []A, int []B){
        int result = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        for(int i = 0; i< A.length; i++){
            int bi = B.length - i -1;
            result += A[i] * B[bi];
        }
        return result;
    }
}