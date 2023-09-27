//https://school.programmers.co.kr/learn/courses/30/lessons/42583
import java.util.*;

class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<int[]> bridge = new LinkedList<>();
        int w = weight;
        Queue<Integer> trucks = new LinkedList<>();
        for (int truck : truck_weights) trucks.add(truck);

        int count = trucks.size();
        int time = 1;
        while(count > 0){
            if(!bridge.isEmpty() && time == bridge.peek()[1]){
                time = bridge.peek()[1];
                w += bridge.poll()[0];
                count--;
            }
            if(!trucks.isEmpty() && w >= trucks.peek()){
                bridge.add(new int[]{trucks.peek(), time + bridge_length});
                w -= trucks.poll();
                time++;
            } else {
                if(bridge.isEmpty()) break;
                time = bridge.peek()[1];
            }
        }
        return time;
    }
}