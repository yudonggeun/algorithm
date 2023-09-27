//https://school.programmers.co.kr/learn/courses/30/lessons/87390
class Solution {
    public int[] solution(int n, long left, long right) {

        int size = (int) (right - left + 1);

        int[] answer = new int[size];

        for (long i = left; i < right + 1; i++) {
            int index = (int) ((int) i - left);
            answer[index] = getNumber(i, n);
        }
        return answer;
    }

    public int getNumber(long index, long rowSize){
        long row = index / rowSize;
        long col = index % rowSize;

        return (int) (Math.max(row, col) + 1);
    }
}