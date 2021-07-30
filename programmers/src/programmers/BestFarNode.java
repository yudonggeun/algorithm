package programmers;
import java.util.*;

class Solution_BestFarNode {
    int n;
    ArrayList<LinkedList<Integer>> map;
    
    int[] oneToN(){
        int [] array = new int[n];
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean [] isVisited = new boolean[this.n];
        isVisited[0] = true;
        queue.add(new int[]{0, 0});
        while(!queue.isEmpty()){
            int node = queue.peek()[0];
            int move = queue.poll()[1];
            array[node] = move;
            for(int next : map.get(node)){
                if(isVisited[next])
                    continue;
                isVisited[next] = true;
                queue.add(new int[]{next, move+1});
            }
        }
        return array;
    }
    public int solution(int n, int[][] edge) {
        this.n = n;
        this.map = new ArrayList<LinkedList<Integer>>();
        for(int i = 0; i< n; i++){
            map.add(new LinkedList<Integer>());
        }
        for(int [] vertex : edge){
            int node1 = vertex[0]-1;
            int node2 = vertex[1]-1;
            map.get(node1).add(node2);
            map.get(node2).add(node1);
        }
        //1번 노드에서 n노드로 이동할 때 최소 간선을 구한다.
        int [] minMove = oneToN();
        //가장 멀리 떨어진 노드의 간선 개수를 확인
        int max = 0;
        for(int move : minMove){
            max = Math.max(max, move);
        }
        //확인한 간선과 일치하는 노드를 검사
        int maxCount = 0;
        for(int move : minMove){
            if(max == move){
                maxCount++;
            }
        }
        return maxCount;
    }
}
public class BestFarNode {
	static int [][][] vertex = {
			{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}
	};
	static int [] n = {
			6
	};
	static int [] answer = {
			3
	};
	public static void main(String[] args) {
		Solution_BestFarNode s = new Solution_BestFarNode();
		for(int i =0; i< answer.length; i++) {
			if(s.solution(n[i], vertex[i]) == answer[i]) {
				System.out.println("테스트 "+ (i+1) + " : 성공");
			}
			else {
				System.out.println("테스트 "+ (i+1) + " : 실패");
			}
		}
	}

}
