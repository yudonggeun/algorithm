package org.example.programmers;
import java.util.*;

class Solution_kakao_friends_coloring_book{
	int[][] next = {{-1, 0},/*��*/{1, 0},/*��*/{0, -1},/*��*/	{0, 1}/*��*/};
	boolean [][] check;
	
	private int getSize(int i, int j, int[][] picture) {
		if(check[i][j]) return 0;//Ȯ���ϴ� ������ �̹� Ȯ���ߴٸ� Ȯ���� �ʿ䰡 ����
		if(picture[i][j] == 0) return 0;//������ ��ĥ�Ǿ����� �ʴٸ� Ȯ���� �ʿ䰡 ����
		int size = 0;
		int color = picture[i][j];
		int p[] = {i, j};
		Stack<int[]> stack = new Stack<int[]>();//x, y�� �����ϴ� ����
		
		stack.add(p.clone());
		while(!stack.isEmpty()) {//������ ��� ����
			p = stack.pop();//�ĺ� ���� ���ÿ��� ����
			if(p[0] >= 0 && p[1] >= 0 && p[0] < picture.length && p[1] < picture[0].length && picture[p[0]][p[1]] == color && !check[p[0]][p[1]]) {
				//������ �����ϰ� �ش� ������ ������ ���� ����
				size++;
				check[p[0]][p[1]] = true;
				for(int [] n : next) {
					int [] temp = p.clone();
					temp[0] += n[0]; temp[1] += n[1];//���� ������ ���ɼ��� �ִ� ��ǥ�� ���ÿ� ����
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
		Solution_kakao_friends_coloring_book s = new Solution_kakao_friends_coloring_book();
		int [][] test;
		int [] test_answer;
		for(int i = 0; i< testCase.size(); i++) {
			test = testCase.get(i);
			test_answer = s.solution(test.length, test[0].length, test);
			if(test_answer[0] == answer.get(i)[0] && test_answer[1] == answer.get(i)[1]) {
				System.out.println("�׽�Ʈ " + (i+1)+ " : " + "����");
			}
			else {
				System.out.println("�׽�Ʈ" + (i+1)+ " : " + "����");
			}
		}
	}

}
