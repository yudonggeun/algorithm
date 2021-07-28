package baekjoon;
import java.io.*;
import java.util.*;

class Point_2178{
	int row;
	int col;
	int depth;
	Point_2178(int row, int col, int depth){
		this.row = row;
		this.col = col;
		this.depth = depth;
	}
}
public class baekjoon2178{
	static int N, M;
	static int [] moveRow = {-1, 1, 0, 0};
	static int [] moveCol = {0, 0, -1, 1};
	static Queue<Point_2178> queue;
	static HashSet<String> hash;
	static int bfs(boolean[][] miro) {
		int answer = 0;
		queue.add(new Point_2178(1, 1, 1));
		while(!queue.isEmpty()) {
			Point_2178 p = queue.poll();
			if(p.row == N && p.col == M) {
				answer = p.depth;
			}
			for(int i = 0; i< 4; i++) {
				int next_row = p.row+moveRow[i];
				int next_col = p.col+moveCol[i];
				int next_depth = p.depth+1;
				String Point_2178_s = next_row + "," + next_col;
				if(!(0 < p.row && p.row <= N && 0 < p.col && p.col <= M)) continue;
				if(!miro[p.row-1][p.col-1]) continue;
				if(hash.contains(Point_2178_s)) continue;
				hash.add(Point_2178_s);
				queue.add(new Point_2178(next_row, next_col, next_depth));
			}
		}
		return answer;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String [] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		boolean [][] miro = new boolean[N][M];
		for(int i = 0; i<N; i++) {
			input = br.readLine().split("");
			for(int j= 0; j<M; j++) {
				if(input[j].equals("1")) miro[i][j] = true;
				else miro[i][j] = false;
			}
		}
		//
		queue = new LinkedList<Point_2178>();
		hash = new HashSet<String>();
		bw.write(bfs(miro)+"");
		bw.flush();
		bw.close();
		br.close();
	}
}
