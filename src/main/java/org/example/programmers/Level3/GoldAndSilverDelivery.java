package org.example.programmers.Level3;

//https://school.programmers.co.kr/learn/courses/30/lessons/86053
class GoldAndSilverDelivery{
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0;
        long right = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (isPossible(mid, a, b, g, s, w, t)) right = mid - 1;
            else left = mid + 1;
        }

        return left;
    }

    public boolean isPossible(long time, int gold, int silver,
                              int[] eachGold, int[] eachSilver, int[] weigh, int[] moveTime) {
        long total = 0;
        long totalGold = 0;
        long totalSilver = 0;

        for (int c = 0; c < eachGold.length; c++) {
            long cnt = time / moveTime[c];
            cnt = cnt % 2 == 1 ? cnt / 2 + 1 : cnt / 2;

            long w = cnt * weigh[c];
            if(w < 0) return true;
            total += Math.min(w, eachGold[c] + eachSilver[c]);
            totalGold += Math.min(w, eachGold[c]);
            totalSilver += Math.min(w, eachSilver[c]);
        }

        return total >= gold + silver && totalGold >= gold && totalSilver >= silver;
    }
}