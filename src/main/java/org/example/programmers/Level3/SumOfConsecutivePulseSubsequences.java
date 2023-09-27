package org.example.programmers.Level3;

class SumOfConsecutivePulseSubsequences{
    public long solution(int[] sequence) {

        for (int i = 0; i < sequence.length; i += 2) sequence[i] *= -1;

        long[] sum = new long[sequence.length];
        sum[0] = sequence[0];
        
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + sequence[i];
        }
        
        long min = 0, max = 0;
        for (int i = 0; i < sum.length; i++) {
            min = Math.min(min, sum[i]);
            max = Math.max(max, sum[i]);
        }

        long answer = 0;

        answer = Math.max(Math.abs(max), Math.abs(min));
        answer = Math.max(answer, Math.abs(max - min));

        return answer;
    }
}