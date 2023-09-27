package org.example.programmers.Level3;//https://school.programmers.co.kr/learn/courses/30/lessons/17676
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Traffic{

    public int solution(String[] lines) {

        AtomicInteger answer = new AtomicInteger();
        Queue<Log> set = new PriorityQueue<>(Comparator.comparingInt(log -> log.end));
        Arrays.stream(lines)
                .map(Log::new)
                .sorted(Comparator.comparing(log -> log.start))
                .forEach(log -> {
                    while (!set.isEmpty() && set.peek().end < log.start){
                        set.poll();
                    }
                    set.add(log);
                    answer.set(Math.max(answer.get(), set.size()));
                });

        return answer.get();
    }

    class Log {
        int start, end;

        Log(String line) {
            int[] result = times(line);
            start = Math.max(result[0] - 999, 0);
            end = Math.max(result[1], 0);
        }
    }

    public int[] times(String line) {
        line = line.replaceFirst("2016-09-15 ", "");
        StringTokenizer firstTokenizer = new StringTokenizer(line);
        StringTokenizer secondTokenizer = new StringTokenizer(firstTokenizer.nextToken(), ":");
        int hour = Integer.parseInt(secondTokenizer.nextToken());
        int minute = Integer.parseInt(secondTokenizer.nextToken());
        int milliSecond = toMillisecond(secondTokenizer.nextToken());
        int duration = toMillisecond(firstTokenizer.nextToken().replace("s", "")) - 1;

        int end_time = toMillisecond(hour, minute, milliSecond);
        int start_time = end_time - duration;
        return new int[]{start_time, end_time};
    }

    public int toMillisecond(int hour, int minute, int milliSecond){
        return Math.max(0, hour * 3600_000 + minute * 60_000 + milliSecond);
    }

    public int toMillisecond(String input) {
        return Integer.parseInt(
                String.format("%.3f", Double.parseDouble(input))
                        .replace(".", "")
        );
    }
}