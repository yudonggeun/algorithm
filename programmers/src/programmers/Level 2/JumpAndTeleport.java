//https://school.programmers.co.kr/learn/courses/30/lessons/12980
import java.util.*;

public class Solution {
    public int solution(int n) {
        return Integer.toString(n, 2).replace("0", "").length();
    }
}