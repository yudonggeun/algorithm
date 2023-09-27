package org.example.programmers;
import java.util.*;

class Solution_Network {
    boolean [] isVisited;
    void bfs(int start, int[][] computers){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        isVisited[start] = true;
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(int next = 0; next< computers[node].length; next++){
                if(computers[node][next] == 0)
                    continue;
                if(isVisited[next])
                    continue;
                queue.add(next);
                isVisited[next] = true;
            }
        }
    }
    public int solution(int n, int[][] computers) {
        int answer = 0;
        isVisited = new boolean[n];
        for(int i = 0; i< n; i++){
            if(isVisited[i] == false){
                bfs(i, computers);
                answer++;
            }
        }
        return answer;
    }
}
public class Network {
	static int [] testSize = { 3, 3 };
	static int [][][] testComputers = {
			{{1,1,0}, {1,1,0}, {0,0,1}},
			{{1,1,0}, {1,1,1}, {0,1,1}}
	};
	static int [] answer = {2, 1};
	
	public static void main(String[] args) {
		Solution_Network s = new Solution_Network();
		for(int i = 0; i< answer.length; i++) {
			if(answer[i] == s.solution(testSize[i], testComputers[i])){
				System.out.println("�׽�Ʈ " + (i+1) + " ����");
			}
			else {
				System.out.println("�׽�Ʈ " + (i+1) + " ����");
			}
				
		}
	}

}
