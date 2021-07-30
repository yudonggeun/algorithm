package programmers;

class Solution_TargetNumber {
    int dfs(int[] numbers, int target, int depth, int sum){
        if(depth == numbers.length){
            if(sum == target)
                return 1;
            else
                return 0;
        }
        int caseCount = 0;
        caseCount += dfs(numbers, target, depth+1, sum+numbers[depth]);
        caseCount += dfs(numbers, target, depth+1, sum-numbers[depth]);
       
        return caseCount;
    }
    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }
}
public class TargetNumber {
	static int [][] testCase = {
			{1,1,1,1,1}
	};
	static int [] target = {
			3
	};
	static int [] answer = {
			5
	};
	public static void main(String[] args) {
		Solution_TargetNumber s = new Solution_TargetNumber();
		for(int i = 0; i < answer.length; i++) {
			if(answer[i] == s.solution(testCase[i],target[i])) {
				System.out.println("테스트 "+ (i+1) + " : 성공");
			}
			else {
				System.out.println("테스트 "+ (i+1) + " : 실패");
			}
		}
	}

}
