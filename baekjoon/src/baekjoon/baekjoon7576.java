package baekjoon;

import java.io.*;
import java.util.*;

public class baekjoon7576 {
	static final int TOMATO = 1;
	static final int GREEN = 0;
	static final int EMPTY = -1;
	static final int [] mRow = {0, 0, 1, -1};
	static final int [] mCol = {1, -1, 0, 0};
	
	static Queue<int[]> queue;
	static int bfs(int[][] box, int green) {
		int answer = 0;
		int gCount = 0;
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			answer = p[2];
			for(int i = 0; i< 4; i++) {
				int nextR = p[0]+mRow[i];
				int nextC = p[1]+mCol[i];
				if(nextR < 0 || nextR >= box.length || nextC < 0 || nextC >= box[0].length) 
					continue;
				else if(box[nextR][nextC] != GREEN) continue;
				box[nextR][nextC] = TOMATO;
				gCount++;
				queue.add(new int[] {nextR, nextC, p[2]+1});
			}
		}
		answer = gCount == green ? answer : -1;
		return answer;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int GreenTomatoCount = 0;
		int row = Integer.parseInt(input[1]);
		int col = Integer.parseInt(input[0]);
		int [][] box = new int[row][col];
		queue = new LinkedList<int[]>();
		for(int i = 0; i< row; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < col ;j++) {
				box[i][j] = Integer.parseInt(input[j]);
				if(box[i][j] == 1) queue.add(new int[] {i, j, 0});
				else if(box[i][j] == 0) GreenTomatoCount++;
			}
		}
		//
		bw.write(bfs(box, GreenTomatoCount)+"");
		bw.flush();
	}

}
