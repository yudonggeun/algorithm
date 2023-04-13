//https://school.programmers.co.kr/learn/courses/30/lessons/12978
import java.util.*;

class Solution {
    
    public int solution(int N, int[][] road, int K) {
        //init
        minPath = new int[N+1];
        Arrays.fill(minPath, Integer.MAX_VALUE);
        costs = new int[N+1][N+1];
        for(int r = 0; r < N+1; r++){
            Arrays.fill(costs[r], Integer.MAX_VALUE);
        }
        for(int[] r : road){
            map.putIfAbsent(r[0], new HashSet<>());
            map.putIfAbsent(r[1], new HashSet<>());
            
            map.get(r[0]).add(r[1]);
            map.get(r[1]).add(r[0]);
            
            costs[r[0]][r[1]] = Math.min(r[2], costs[r[0]][r[1]]);
            costs[r[1]][r[0]] = costs[r[0]][r[1]];
        }
        
        //
        update();
        int answer = 0;
        for(int path: minPath){
            if(path<= K) answer++;
        }
        
        return answer;
    }
    
    int[] minPath;
    Map<Integer, Set<Integer>> map = new HashMap<>();
    int[][] costs;
    
    public void update(){
        Queue<Node> queue = new LinkedList<>();
        minPath[1] = 0;
        queue.add(new Node(1, 0));
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            for(int next : map.get(node.location)){
                int nextCost = node.cost + costs[node.location][next];
                if(nextCost < minPath[next]){
                    minPath[next] = nextCost;
                    queue.add(new Node(next, nextCost));
                }
            }
        }
    }
    
    class Node{
        int location;
        int cost;
        
        Node(int location, int cost){
            this.location = location;
            this.cost = cost;
        }
    }
}