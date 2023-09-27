//https://school.programmers.co.kr/learn/courses/30/lessons/12915
import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings);
        Arrays.sort(strings, Comparator.comparing(o -> o.charAt(n)));
        return strings;
    }
}