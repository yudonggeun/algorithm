package org.example.programmers;
class Solution_SpecialNumSystem {
    public String solution(int n) {
        String num[] = {"1", "2", "4"};
        String answer = "";
        while(n > 0){
            int index = (n-1)%3;
            answer = num[index] + answer;
            n -= index+1;
            n /= 3;
        }
        return answer;
    }
}
public class SpecialNumSystem {
	static int [] testCase = {1, 2, 3, 4, 10};
	static String [] answer = {"1", "2", "4", "11", "41"};
	public static void main(String[] args) {
		Solution_SpecialNumSystem s = new Solution_SpecialNumSystem();
		for(int i = 0; i < testCase.length; i++) {
			if(s.solution(testCase[i]).equals(answer[i])) {
				System.out.println("�׽�Ʈ " + (i+1) + " ����");
			}
			else {
				System.out.println("�׽�Ʈ " + (i+1) + " ����");
			}
		}
	}

}
