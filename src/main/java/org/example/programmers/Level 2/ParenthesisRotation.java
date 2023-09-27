//https://school.programmers.co.kr/learn/courses/30/lessons/76502#
import java.util.*;

class Solution {
    public int solution(String s) {
        init();
        int count = 0;
        for (int i = 0; i < s.length(); i++) if (valid(s, i)) count++;
        return count;
    }

    Set<Character> front = new HashSet<>();
    Map<Character, Character> map = new HashMap<>();

    public void init() {
        front.add('[');
        front.add('{');
        front.add('(');

        map.put('[', ']');
        map.put('{', '}');
        map.put('(', ')');
    }

    public boolean valid(String s, int rotation) {
        int index = rotation;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char target = s.charAt(index);
            if (front.contains(target)) {
                stack.add(target);
            } else {
                if (stack.isEmpty()) return false;
                if (map.get(stack.pop()) != target) return false;
            }
            index = nextIndex(s, index);
        }
        return stack.isEmpty();
    }

    public int nextIndex(String s, int index) {
        int next = index + 1;
        if (next == s.length()) return 0;
        return next;
    }
}