//https://school.programmers.co.kr/learn/courses/30/lessons/77885
import java.util.LinkedList;
import java.util.List;

class Solution {

    public static void main(String[] args) {
        Solution se = new Solution();
        System.out.println(se.diff(7, 8));
        System.out.println(se.add(2));
        System.out.println(se.add(se.diff(7, 8)));
    }

    public long[] solution(long[] numbers) {
        List<Long> result = new LinkedList<>();

        for (long number : numbers) {
            long answer = number + 1;
            long xor = answer ^ number;
            long add = (Long.MAX_VALUE ^ answer) & xor;
            while (true) {
                int diff = diff(number, answer);
                if(diff <= 2) break;
                answer += add(diff);
            }
            result.add(answer);
        }
        return result.stream().mapToLong(Long::longValue).toArray();
    }

    public long add(int diff) {
        long result = 1;
        diff -= 2;
        while (diff > 1) {
            result += Math.pow(2, diff - 1);
            diff--;
        }
        return result;
    }

    public int diff(long a, long b) {
        long xor = a ^ b;
        int count = 0;
        while (xor != 0) {
            count += xor % 2;
            xor = xor / 2;
        }
        return count;
    }
}