package org.example.programmers.Level3;//https://school.programmers.co.kr/learn/courses/30/lessons/17678
import java.util.*;

class SuttleBus{
    public String solution(int n, int t, int m, String[] timetable) {
        Bus bus = new Bus(m, t, new Time("09:00"));

        Object[] array = Arrays.stream(timetable).sorted().map(Time::new).toArray();
        for (int i = 0; i < array.length; i++) {
            Time time = (Time) array[i];
            if (!bus.isFull() && time.value <= bus.timeOfArrival.value) {
                bus.passengers.add(time);
                continue;
            }
            if (n == 1) break;
            bus.nextBus();
            n--;
            i--;
        }

        while (n-- > 1) bus.nextBus();
        
        Time result;
        if (!bus.isFull()) result = bus.timeOfArrival;
        else {
            result = bus.passengers.get(bus.passengers.size() - 1);
            result.value--;
        }
        return result.toString();
    }

    class Bus {
        int intervalOfDispatch;
        int capacity;
        List<Time> passengers = new LinkedList<>();
        Time timeOfArrival;

        Bus(int capacity, int intervalOfDispatch, Time timeOfArrival){
            this.capacity = capacity;
            this.intervalOfDispatch = intervalOfDispatch;
            this.timeOfArrival = timeOfArrival;
        }

        public boolean isFull(){
            return passengers.size() == capacity;
        }

        public void nextBus() {
            timeOfArrival.add(intervalOfDispatch);
            passengers.clear();
        }
    }

    class Time {
        int value;

        Time(String s) {
            StringTokenizer st = new StringTokenizer(s, ":");
            value = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
        }

        public void add(int min) {
            value += min;
        }

        public String toString() {
            int hour = value / 60;
            int minute = value % 60;
            return String.format("%02d:%02d", hour, minute);
        }
    }
}