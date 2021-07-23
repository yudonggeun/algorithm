package baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon14502 {
	static int answer, zeros;
	static Vector<int[]> virus;
	static final int [] mRow = {0, 0, 1, -1};
	static final int [] mCol = {1, -1, 0, 0};
	static void bfs(int[][] lab) {
		int count = 0;
		Queue<int[]> queue = new LinkedList<int[]>();
		HashSet<String> hash = new HashSet<String>();
		for(int [] v : virus) {
			queue.add(v);
		}
		while(!queue.isEmpty()) {
			int[] move = queue.poll();
			for(int i = 0; i< 4; i++) {
				int[] next = {move[0]+mRow[i], move[1]+mCol[i]};
				if(next[0] < 0 || next[1] < 0 || next[0] >= lab.length || next[1] >= lab[0].length)
					continue;
				if(lab[next[0]][next[1]] != 0) continue;
				if(hash.contains(next[0]+","+next[1])) continue;
				hash.add(next[0]+","+next[1]);
				queue.add(next);
				count++;
			}
		}
		System.out.println(zeros - count);
		answer = Math.max(answer, zeros - count);
	}
	static void makeCase(int[][] lab) {
		int size = lab.length*lab[0].length;
		for(int i = 1; i<= size; i++) {
			if(nthValue(lab, i) != 0) 
				continue;
			for(int j = i+1; j <= size; j++) {
				if(nthValue(lab, j) != 0) 
					continue;
				for(int k = j+1; k <= size; k++) {
					if(nthValue(lab, k) != 0)
						continue;
					setValue(lab, i, 1); setValue(lab, j, 1); setValue(lab, k, 1);
					print(lab);
					bfs(lab);
					setValue(lab, i, 0); setValue(lab, j, 0); setValue(lab, k, 0);
				}
				
			}
			
		}
	}
	static void print(int[][] lab) {
		for(int [] n : lab) {
			for(int k : n)
				System.out.print(k+" ");
			System.out.println();
		}
	}
	static int nthValue(int[][] lab, int n) {
		n--;
		int row = n/lab[0].length;
		int col = n%lab[0].length;
		return lab[row][col];
	}
	static void setValue(int[][] lab, int n, int value) {
		n--;
		int row = n/lab[0].length;
		int col = n%lab[0].length;
		lab[row][col] = value;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		answer = 0;	zeros = -3;
		virus = new Vector<int[]>();
		String [] input = br.readLine().split(" ");
		int row = Integer.parseInt(input[0]);
		int col = Integer.parseInt(input[1]);
		int[][] lab = new int[row][col];
		for(int i = 0; i< row; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j< col; j++) {
				lab[i][j] = Integer.parseInt(input[j]);
				if(lab[i][j] == 0) zeros++;
				else if(lab[i][j] == 2) virus.add(new int[]{i, j});
			}
		}
		//
		makeCase(lab);
		bw.write(answer+"");
		bw.flush();
	}

}
