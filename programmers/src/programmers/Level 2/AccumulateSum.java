//https://school.programmers.co.kr/learn/courses/30/lessons/178870
class Solution {
    public int[] solution(int[] sequence, int k) {
        int start = sequence.length - 1;
        int end = sequence.length - 1;

        int sum = 0;
        int len = 0;

        for (; start >= 0; start--) {
            sum += sequence[start];

            if (sum == k) {
                len = end - start + 1;
                break;
            } else if (sum > k) {
                sum -= sequence[end];
                end--;
            }
        }

        return getCase(sequence, len, k);
    }

    public int[] getCase(int[] sequence, int len, int target) {
        int sum = 0;
        for (int i = 0; i < len; i++) sum += sequence[i];

        if (sum == target) return new int[]{0, len - 1};
        for (int i = len; i < sequence.length; i++) {
            sum += sequence[i] - sequence[i - len];
            if (sum == target) return new int[]{i - len + 1, i};
        }
        return null;
    }
}