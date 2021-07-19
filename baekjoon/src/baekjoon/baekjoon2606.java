package baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon2606 {
	static int bfs(Vector<Vector<Integer>> map, int startIndex) {
		int count = 0;
		Queue<Integer> queue = new LinkedList<Integer>();
		HashSet<Integer> hash = new HashSet<Integer>();
		queue.add(startIndex); hash.add(startIndex);
		while(!queue.isEmpty()) {
			int node = queue.poll();
			for(Integer next : map.elementAt(node)) {
				if(hash.contains(next)) continue;
				queue.add(next);
				hash.add(next);
				count++;
			}
		}
		return count;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int nodeCount = Integer.parseInt(br.readLine());
		int line = Integer.parseInt(br.readLine());
		Vector<Vector<Integer>> map = new Vector<Vector<Integer>>();
		for(int i = 0; i< nodeCount; i++) map.add(new Vector<Integer>());
		for(int i = 0; i< line; i++) {
			String [] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]) - 1;
			int b = Integer.parseInt(input[1]) - 1;
			map.elementAt(a).add(b);
			map.elementAt(b).add(a);
		}
		bw.write(bfs(map, 0)+"");
		bw.flush();
	}

}
