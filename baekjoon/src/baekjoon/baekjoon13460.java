package baekjoon;
import java.io.*;
import java.util.*;

class Ball{
	int row, col, moveCount;
	char color;
	Board board;
	final int [] mRow = {-1, 1, 0, 0};
	final int [] mCol = {0, 0, -1, 1};
	Ball(int row, int col,int moveCount, char color, Board board){
		this.row = row; this.col = col;
		this.moveCount = moveCount;
		this.color = color;
		this.board = board;
	}
	void move(int type) {
		moveCount++;
		board.clear(color);
		row += mRow[type];
		col += mCol[type];
		while(board.isRange(row, col)) {
			if(board.isExit(row, col)) {
				color = 'O';
				return;
			}
			else if(!board.isblank(row, col)) {
				row -= mRow[type];
				col -= mCol[type];
				board.setBalls(this, color);
				break;
			}
			row += mRow[type];
			col += mCol[type];
		}
	}
	protected Ball clone() {
		return new Ball(row, col, moveCount, color, board);
	}
	@Override
	public String toString() {
		return row+","+col;
	}
}
class Board{
	char[][] board;
	Ball red, blue;
	void print() {
		for(char[] a : board) {
			System.out.println(a);
		}
	}
	Board(char[][] board){
		this.board = board;
		red = null; blue = null;
	}
	void move(int type) {
		if(this.isRedFirst(type)) {
			red.move(type);
			blue.move(type);
		}
		else {
			blue.move(type);
			red.move(type);
		}
	}
	void clear(char color) {
		if(color == 'R') {
			board[red.row][red.col] = '.';
		}
		else {
			board[blue.row][blue.col] = '.';
		}
	}
	void setBalls(Ball ball, char color) {
		if(color == 'R') {
			board[red.row][red.col] = '.';
			red = ball;
			board[red.row][red.col] = 'R';
		}
		else {
			board[blue.row][blue.col] = '.';
			blue = ball;
			board[blue.row][blue.col] = 'B';
		}
	}
	boolean isblank(int row, int col) {
		return board[row][col] == '.';
	}
	boolean isExit(int row, int col) {
		return board[row][col] == 'O';
	}
	boolean isRange(int row, int col) {
		return -1 < row && row < board.length && -1 < col && col < board[0].length;
	}
	boolean isRedFirst(int type) {
		switch(type) {
		case 0: //up
			return red.row < blue.row;
		case 1: //down
			return red.row > blue.row;
		case 2: //left
			return red.col < blue.col;
		case 3:
			return red.col > blue.col;
		}
		return true;
	}
	Ball[] nextBall(int type) {
		Ball[] pre = {red.clone(), blue.clone()};
		if(isRedFirst(type)) {
			red.move(type);
			blue.move(type);
		}
		else {
			blue.move(type);
			red.move(type);
		}
		Ball[] next = new Ball[] {red, blue};
		this.red = pre[0]; this.blue = pre[1];
		return next;
	}
}
public class baekjoon13460 {
	static int bfs(Ball r, Ball b, Board board) {
		Queue<Ball> redQ = new LinkedList<Ball>();
		Queue<Ball> blueQ = new LinkedList<Ball>();
		HashSet<String> redH = new HashSet<String>();
		HashSet<String> blueH = new HashSet<String>();
		redQ.add(r); blueQ.add(b);
		redH.add(r.toString()); blueH.add(b.toString());
		while(!redQ.isEmpty()) {
			Ball red = redQ.poll();
			Ball blue = blueQ.poll();
			for(int i = 0; i< 4; i++) {
				board.setBalls(red.clone(), 'R');
				board.setBalls(blue.clone(), 'B');
				System.out.println("p");
				board.print();
				board.move(i);
				if(redH.contains(board.red.toString()) && blueH.contains(board.blue.toString())) {
					continue;
				}
				if(board.blue.color == 'O') continue;
				if(board.red.color == 'O') return board.red.moveCount;
				System.out.println("n");
				board.print();
				redQ.add(board.red);
				blueQ.add(board.blue);
				redH.add(board.red.toString());
				blueH.add(board.blue.toString());
			}
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		int answer = -1;
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String [] input = br.readLine().split(" ");
		int rowCount = Integer.parseInt(input[0]);
		int colCount = Integer.parseInt(input[1]);
		char[][] board = new char[rowCount][colCount];
		Board b = new Board(board);
		Ball red = null, blue = null;
		for(int i = 0; i < rowCount; i++) {
			String line = br.readLine().trim();
			for(int j = 0; j < colCount; j++) {
				board[i][j] = line.charAt(j);
				if(board[i][j] == 'R')
					red = new Ball(i, j, 0, 'R', b);
				else if(board[i][j] == 'B')
					blue = new Ball(i, j, 0, 'B', b);
			}
		}
		//bfs()
		b.red = red; b.blue = blue;
		answer = bfs(red, blue, b);
		//출력
		bw.write(answer + "");
		bw.flush();
		bw.close();
		br.close();
	}
}
		
//https://www.acmicpc.net/problem/13460