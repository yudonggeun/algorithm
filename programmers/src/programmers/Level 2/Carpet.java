//https://school.programmers.co.kr/learn/courses/30/lessons/42842
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = (brown - 4) / 2;

        for(int x = 1; x < sum; x++){
            int y = sum - x;
            if(x*y == yellow){
                x+=2; y+=2;
                answer[0] = Math.max(x, y);
                answer[1] = Math.min(x, y);
                return answer;
            }
        }
        return answer;
    }
}