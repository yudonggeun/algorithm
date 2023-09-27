package org.example.programmers;
import java.util.*;

class Solution_FunctionDevelopment {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for(int i = 0, day = 0, count = 0; i < progresses.length;){
            if(progresses[i] + day*speeds[i] >= 100){
                count++;
                i++;
                if(i == progresses.length)
                    answer.add(count);
                continue;
            }
            else if(count > 0){
                answer.add(count);
                count = 0;
            }
            day++;
        }
        int [] fin = new int [answer.size()];
        for(int i = 0; i< answer.size(); i++){
            fin[i] = answer.get(i);
        }
        return fin;
    }
}
public class FunctionDevelopment {
	static int[][] progresses = {
			{93, 30, 55},
			{95, 90, 99, 99, 80, 99}
	};
	static int[][] speeds = {
			{1, 30, 5},
			{1,1,1,1,1,1}
	};
	static int [][] answer = {
			{2,1},
			{1,3,2}
	};
	static boolean isIntArrayEquals(int[] a, int[] b) {
		if(a.length != b.length)
			return false;
		for(int i = 0; i< a.length; i++) {
			if(a[i] != b[i])
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Solution_FunctionDevelopment s = new Solution_FunctionDevelopment();
		for(int i = 0; i < answer.length; i++) {
			if(isIntArrayEquals(answer[i], s.solution(progresses[i], speeds[i]))) {
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
			else {
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
		}
	}

}
