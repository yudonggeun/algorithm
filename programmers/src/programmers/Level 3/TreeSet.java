//https://school.programmers.co.kr/learn/courses/30/lessons/42628
import java.util.TreeSet;

class Solution {
    public int[] solution(String[] operations) {

        TreeSet<Integer> set = new TreeSet<>();

        for (String operation : operations) {
             if(operation.equals("D 1")){
                 set.pollLast();
             } else if(operation.equals("D -1")){
                 set.pollFirst();
             } else {
                 String[] o = operation.split(" ");
                 Integer num = Integer.parseInt(o[1]);
                 set.add(num);
             }
        }

        Integer min = set.pollFirst();
        Integer max = set.pollLast();

        if(min == null) return new int[]{0, 0};
        else if(max == null) return new int[]{min, min};
        return new int[]{max, min};
    }
}