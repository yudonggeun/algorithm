package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/42885#
import java.util.*;

class RescueBoat{

    public int solution(int[] people, int limit) {

        int left = 0, right = people.length - 1;
        int count = 0;

        Arrays.sort(people);
        while (left < right) {
            int lp = people[left], rp = people[right];
            if (lp + rp > limit) {
                right--;
            } else {
                left++;
                right--;
            }
            count++;
        }

        if(left == right) count++;
        return count;
    }
}