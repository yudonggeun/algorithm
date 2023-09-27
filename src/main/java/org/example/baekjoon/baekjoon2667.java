package org.example.baekjoon;

import java.util.*;
import java.io.*;

class Dot_2667{
	int row;
	int col;
	Dot_2667(int row, int col){
		this.row = row;
		this.col = col;
	}
}
public class baekjoon2667 {
	static int moveRow[] = {0, 0, 1, -1};
	static int moveCol[] = {1, -1, 0, 0};
	static Vector<Integer> houseCount;
	static void bfs(boolean [][] area, int start_r, int start_c) {
		Queue<Dot_2667> queue = new LinkedList<Dot_2667>();
		int count = 0;
		queue.add(new Dot_2667(start_r, start_c));
		while(!queue.isEmpty()) {
			Dot_2667 p = queue.poll();
			if(!(0 <= p.row && p.row < area.length && 0 <= p.col && p.col < area[0].length)) continue;
			if(!area[p.row][p.col]) continue;
			count++;
			area[p.row][p.col]= false;
			for(int i = 0; i< 4; i++) {
				queue.add(new Dot_2667(p.row + moveRow[i], p.col + moveCol[i]));
			}
		}
		houseCount.add(count);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int lineNum = Integer.parseInt(br.readLine());
		boolean area[][] = new boolean[lineNum][lineNum];
		for(int i = 0; i< lineNum ; i++) {
			String input = br.readLine();
			for(int j = 0; j< lineNum; j++) {
				area[i][j] = input.charAt(j) == '0' ? false : true;
			}
		}
		//
		houseCount = new Vector<Integer>();
		for(int i = 0; i< lineNum; i++) {
			for(int j = 0; j< lineNum; j++) {
				if(area[i][j]) bfs(area, i, j);
			}
		}
		Collections.sort(houseCount);
		bw.write(houseCount.size()+"\n");
		for(int answer : houseCount) {
			bw.write(answer+"\n");
		}
		bw.flush();
	}

}
