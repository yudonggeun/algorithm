package org.example.programmers.Level1;//https://school.programmers.co.kr/learn/courses/30/lessons/155652
import java.util.*;

class OurPassword{
    public String solution(String s, String skip, int index) {
        List<Character> dict = new ArrayList<>();
        for(char c = 'a'; c <= 'z'; c++){
            dict.add(c);
        }
        
        for(Character removeC : skip.toCharArray()){
            dict.remove(removeC);
        }
        System.out.println(dict);
        char[] array = s.toCharArray();
        
        for(int i = 0; i< array.length; i++){
            int t = index + dict.indexOf(array[i]);
            t = t % (dict.size());
            array[i] = dict.get(t);
        }
        
        return String.valueOf(array);
    }
}