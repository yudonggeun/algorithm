package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/12951
class JadenCase  {
    public String solution(String s) {
        char[] array = s.toLowerCase().toCharArray();
        
        boolean space = true;
        
        for(int i = 0; i< array.length; i++){
            if(array[i] == ' '){
                space = true;
            } else if(space && 'a' <= array[i] && array[i] <= 'z'){
                array[i] += 'A' -'a';
                space = false;
            } else {
                space = false;
            }
        }
        return String.valueOf(array);
    }
}