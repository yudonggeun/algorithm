//https://school.programmers.co.kr/learn/courses/30/lessons/86971
import java.util.*;

class Solution {

    Map<Integer, Set<Integer>> map = new HashMap<>();

    public int solution(int n, int[][] wires) {
        int answer = n;

        init(wires);

        for (int[] wire : wires){
            int treeA = getCount(wire[0], wire[1]);
            int treeB = getCount(wire[1], wire[0]);

            answer = Math.min(answer, Math.abs(treeA - treeB));
        }
        return answer;
    }

    void init(int[][] wires){
        for (int [] wire : wires){
            map.putIfAbsent(wire[0], new HashSet<>());
            map.putIfAbsent(wire[1], new HashSet<>());
            map.get(wire[0]).add(wire[1]);
            map.get(wire[1]).add(wire[0]);
        }
    }
    int getCount(int start, int visitNode){

        Set<Integer> visit = new HashSet<>();
        visit.add(visitNode);
        visit.add(start);

        Queue<Integer> next = new LinkedList<>();
        int count = 0;
        next.add(start);

        while(!next.isEmpty()){
            Integer node = next.poll();
            count++;

            for (Integer nextNode : map.get(node)){
                if(visit.contains(nextNode)) continue;
                next.add(nextNode);
                visit.add(nextNode);
            }
        }
        return count;
    }
}