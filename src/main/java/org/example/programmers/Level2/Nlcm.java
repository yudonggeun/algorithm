package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/12953
import java.util.*;

class Nlcm  {
    
    List<Integer> prime = new LinkedList<>();
    
    public int solution(int[] arr) {
        init();
        int[] dp = new int[101];
        for(int num : arr){
            Iterator<Integer> it = prime.iterator();
            while(num != 1 && it.hasNext()){
                int p = it.next();
                int count = 0;
                while(num % p == 0){
                    num = num/p;
                    count++;
                }
                dp[p] = Math.max(dp[p], count);
            }
        }
        
        int answer = 1;
        for(int i = 2; i< dp.length; i++){
            answer *= Math.pow(i, dp[i]);
        }
        return answer;
    }
    
    public void init(){
        for(int i = 2; i< 101; i++){
            if(isPrime(i)){
                prime.add(i);
            }
        }        
    }
    
    public boolean isPrime(int num){
        for(int i = 2; i < Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}