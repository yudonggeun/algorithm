//https://school.programmers.co.kr/learn/courses/30/lessons/161989
class Solution {
    public int solution(int n, int m, int[] section) {

        int target = 0;
        int count = 0;

        for (int index : section) {
            if (target >= index) {//이미 칠한 곳
                continue;
            } else {//칠하지 않은 곳
                count++;
                target = index + m - 1;
            }
        }

        return count;
    }
}