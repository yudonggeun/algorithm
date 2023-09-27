package org.example.programmers.Level1;//https://school.programmers.co.kr/learn/courses/30/lessons/1845
import java.util.*;

class Phonecketmon{
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) set.add(num);
        return (int) Math.min(set.size(), nums.length /2);
    }
}