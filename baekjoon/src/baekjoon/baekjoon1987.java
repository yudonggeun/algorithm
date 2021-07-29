package baekjoon;
import java.io.*;
import java.util.*;

public class baekjoon1987 {
	static char [][] board;
	static HashSet<Character> hash;
	static final int [] mRow = {0, 0, 1, -1};
	static final int [] mCol = {1, -1, 0, 0};
	static int dfs(int row, int col, int depth) {
		if(row >= board.length || row < 0 || col >=board[0].length || col < 0)
			return depth-1;
		if(hash.contains(board[row][col]))
			return depth-1;
		hash.add(board[row][col]);
		int max = 0;
		for(int i = 0; i< 4; i++) {
			int nRow = row + mRow[i];
			int nCol = col + mCol[i];
			max = Math.max(max, dfs(nRow, nCol, depth+1));
		}
		hash.remove(board[row][col]);
		return max;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int row = Integer.parseInt(input[0]);
		board = new char[row][];
		hash = new HashSet<Character>();
		for(int i = 0; i< row; i++) {
			board[i] = br.readLine().trim().toCharArray();
		}
		bw.write(dfs(0, 0, 1)+"");
		bw.flush();
		br.close();
		bw.close();
	}

}
