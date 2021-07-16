package baekjoon;
import java.util.*;
import java.io.*;
//1차 풀이
//스택과 큐를 이용한 간단 bfs dfs 코딩 해보자
public class baekjoon1260 {
	public static void dfs(int target, Vector<Queue<Integer>> map, boolean[] isVisit) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(target);
		while(!queue.isEmpty()) {
			int node = queue.poll();
			if(isVisit[node-1]) continue;
			System.out.print(node + " ");
			isVisit[node-1] = true;
			for(int next_node : map.elementAt(node-1)) {
				queue.add(next_node);
			}
		}
	}
	public static void bfs(int target, Vector<Queue<Integer>> map, boolean[] isVisit) {
		isVisit[target-1] = true;
		Queue<Integer> queue = map.elementAt(target-1);
		for(int node : queue) {
			if(isVisit[node-1]) continue;
			System.out.print(node + " ");
			bfs(node, map, isVisit);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String [] init_information = br.readLine().split(" ");
		int node_count = Integer.parseInt(init_information[0]);
		int line_count = Integer.parseInt(init_information[1]);
		int start_node = Integer.parseInt(init_information[2]);
		Vector<Queue<Integer>> map = new Vector<Queue<Integer>>();
		for(int i = 0; i< node_count; i++) {
			map.add(new LinkedList<Integer>());
		}
		
		for(int i = 0; i< line_count; i++) {
			String [] input = br.readLine().split(" ");
			int pre = Integer.parseInt(input[0]);
			int back = Integer.parseInt(input[1]);
			map.elementAt(pre-1).add(back);
			map.elementAt(back-1).add(pre);
		}
		
		for(int i = 0; i< node_count; i++) {
			Collections.sort((LinkedList)map.elementAt(i));
		}
		
		boolean [] isVisit_dfs = new boolean[node_count]; Arrays.fill(isVisit_dfs, false);
		boolean [] isVisit_bfs = new boolean[node_count]; Arrays.fill(isVisit_bfs, false);
		System.out.print(start_node + " "); 
		bfs(start_node, map, isVisit_bfs);
		System.out.println(); 
		dfs(start_node, map, isVisit_dfs);
	}

}
//https://www.acmicpc.net/problem/1260