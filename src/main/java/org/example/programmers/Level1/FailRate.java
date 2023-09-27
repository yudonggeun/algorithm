package org.example.programmers.Level1;//https://school.programmers.co.kr/learn/courses/30/lessons/42889

import java.util.*;

class FailRate {
    public int[] solution(int N, int[] stages) {
        int[] dp = new int[N + 2];

        for (int stage : stages) {
            dp[stage]++;
        }

        for (int i = dp.length - 2; i > -1; i--) {
            dp[i] += dp[i + 1];
        }

        List<Data> answer = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int num = i + 1;
            double rate = dp[num] != 0 ? (dp[num] - dp[num + 1]) / (double) dp[num] : 0;
            answer.add(new Data(num, rate));
        }
        Collections.sort(answer, (o1, o2) -> {
            return Double.compare(o2.rate, o1.rate);
        });

        return answer.stream().mapToInt(data -> data.stage).toArray();
    }

    class Data {
        double rate;
        int stage;

        Data(int stage, double rate) {
            this.stage = stage;
            this.rate = rate;
        }
    }
}