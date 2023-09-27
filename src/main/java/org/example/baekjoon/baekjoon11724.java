package org.example.baekjoon;

import java.util.*;
import java.io.*;

public class baekjoon11724 {
	static int bfs(Hashtable<Integer, Vector<Integer>> map, HashSet<Integer> check, int node) {
		Queue<Integer> queue = new LinkedList<Integer>();
		if(check.contains(node)) return 0;
		queue.add(node);
		while(!queue.isEmpty()) {
			int p = queue.poll();
			for(int next : map.get(p)) {
				if(check.contains(next))
					continue;
				queue.add(next);
				check.add(next);
			}
		}
		return 1;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int repeat = Integer.parseInt(input[1]);
		int answer = 0;
		Hashtable<Integer, Vector<Integer>> map = new Hashtable<Integer, Vector<Integer>>();
		HashSet<Integer> check = new HashSet<Integer>();
		for(int i = 0; i< n; i++) {
			map.put(i+1, new Vector<Integer>());
		}
		for(int i = 0; i < repeat ; i++) {
			input = br.readLine().split(" ");
			int node1 = Integer.parseInt(input[0]);
			int node2 = Integer.parseInt(input[1]);
			map.get(node1).add(node2);
			map.get(node2).add(node1);
		}
		
		for(int i = 1; i<= n; i++) {
			answer += bfs(map, check, i);
		}
		bw.write(answer+"");
		bw.flush();
	}
}