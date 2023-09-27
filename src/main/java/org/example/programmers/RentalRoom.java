package org.example.programmers;//https://school.programmers.co.kr/learn/courses/30/lessons/155651
import java.util.StringTokenizer;

class RentalRoom{
    public int solution(String[][] book_time) {

        int[] sum = new int[24 * 60 + 10];

        for (String[] time : book_time) {
            Time start = new Time(time[0]);
            Time end = new Time(time[1]);

            sum[start.getIndex()] += 1;
            sum[end.getIndex() + 10] -= 1;
        }

        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i] + sum[i - 1];
        }

        int max = 0;
        for (int i : sum) {
            max = Math.max(i, max);
        }

        return max;
    }

    class Time{
        int hour, minute;

        public Time(String times) {
            StringTokenizer st = new StringTokenizer(times, ":");
            hour = Integer.parseInt(st.nextToken());
            minute = Integer.parseInt(st.nextToken());
        }

        public int getIndex(){
            return hour * 60 + minute;
        }
    }
}