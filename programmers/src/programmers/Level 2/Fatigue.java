//https://school.programmers.co.kr/learn/courses/30/lessons/87946
import java.util.HashSet;
import java.util.Set;

class Solution {

    private int count = 0;

    public int solution(int k, int[][] dungeons) {

        d = dungeons;
        dfs(0, 0, new HashSet<>(), k);

        return count;
    }

    int[][] d;

    private void dfs(int depth, int heat, Set<Integer> visit, int k) {

        if(depth == d.length){
            count = Math.max(count, heat);
            return;
        }
        for (int i = 0; i < d.length; i++) {
            if (visit.contains(i)) continue;

            int limit = d[i][0];
            int cost = d[i][1];
            int nextHeat = k < limit ? heat : heat + 1;
            int nextDepth = depth + 1;
            int nextK = k < limit ? k : k - cost;

            visit.add(i);
            dfs(nextDepth, nextHeat, visit, nextK);
            visit.remove(i);
        }
    }
}