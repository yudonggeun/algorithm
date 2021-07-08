package programmers;
import java.util.*;

class Solution {
	int[][] next = {{-1, 0},/*상*/{1, 0},/*하*/{0, -1},/*좌*/	{0, 1}/*우*/};
	boolean [][] check;
	
	private int getSize(int i, int j, int[][] picture) {
		if(check[i][j]) return 0;//확인하는 영역이 이미 확인했다면 확인할 필요가 없음
		if(picture[i][j] == 0) return 0;//영역이 색칠되어있지 않다면 확인할 필요가 없음
		int size = 0;
		int color = picture[i][j];
		int p[] = {i, j};
		Stack<int[]> stack = new Stack<int[]>();//x, y를 저장하는 스택
		
		stack.add(p.clone());
		while(!stack.isEmpty()) {//스택이 비면 종료
			p = stack.pop();//후보 영역 스택에서 빼기
			if(p[0] >= 0 && p[1] >= 0 && p[0] < picture.length && p[1] < picture[0].length && picture[p[0]][p[1]] == color && !check[p[0]][p[1]]) {
				//범위를 만족하고 해당 색깔이 같으면 같은 영역
				size++;
				check[p[0]][p[1]] = true;
				for(int [] n : next) {
					int [] temp = p.clone();
					temp[0] += n[0]; temp[1] += n[1];//같은 영역일 가능성이 있는 좌표를 스택에 저장
					stack.add(temp);
				}
			}
		}
		return size;
	}
    public int[] solution(int m, int n, int[][] picture) {
    	check = new boolean[m][n];
    	int[] answer = new int[2];
    	int size;
    	for(int i = 0; i< m; i++) {
    		for(int j = 0; j< n; j++) {
    			size = getSize(i, j, picture);
    			if(size > 0) answer[0]++;
    			answer[1] = Math.max(answer[1], size);
    		}
    	}        
        return answer;
    }
}
public class kakao_friends_coloring_book {
	//test case
	static Vector<int[][]> testCase;
	static int[][] testCase1 = {
			{1,1,1,0},
			{1,1,1,0},
			{0,0,0,1},
			{0,0,0,1},
			{0,0,0,1},
			{0,0,0,1}};
	static int[][] testCase2 = {
			{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
			{0,1,1,1,1,3,1,1,1,1,3,1,1,1,1,0},
			{0,1,1,1,3,1,3,1,1,3,1,3,1,1,1,0},
			{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
			{0,1,2,2,2,1,1,1,1,1,1,2,2,2,1,0},
			{0,1,1,1,1,1,3,1,1,3,1,1,1,1,1,0},
			{0,0,1,1,1,1,1,3,3,1,1,1,1,1,0,0},
			{0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0}};
	//answer
	static Vector<int[]> answer;
	static int[] answer1 = {2, 6};
	static int[] answer2 = {12, 120};
	private static void init_testCase() {
		testCase = new Vector<int[][]>();
		testCase.add(testCase1);
		testCase.add(testCase2);
		answer = new Vector<int[]>();
		answer.add(answer1);
		answer.add(answer2);
	}
	public static void main(String[] args) {
		init_testCase();
		Solution s = new Solution();
		int [][] test;
		int [] test_answer;
		for(int i = 0; i< testCase.size(); i++) {
			test = testCase.get(i);
			System.out.println("테스트 " + i + " : 시작");
			test_answer = s.solution(test.length, test[0].length, test);
			if(test_answer[0] == answer.get(i)[0] && test_answer[1] == answer.get(i)[1]) {
				System.out.println("테스트 " + i+ " : " + "성공");
			}
			else {
				System.out.println("테스트" + i+ " : " + "실패");
			}
		}
	}

}
