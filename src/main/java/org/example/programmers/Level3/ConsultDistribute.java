package org.example.programmers.Level3;

import java.util.*;

public class ConsultDistribute {

    public int solution(int type, int n, int[][] reqs) {

        int[] type_tutor = new int[type + 1];

        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int i = 1; i < type + 1; i++){
            map.put(i, new ArrayList<>());
            type_tutor[i]++;
            n--;
        }
        for(int[] req : reqs){
            int t = req[2];
            map.get(t).add(req);
        }

        while(n != 0){
            int t = 1;
            int effect = 0;
            for(int i = 1; i < type + 1; i++){
                List<int[]> r = map.get(i);
                if(r == null || r.isEmpty()) continue;
                int i_effect = sum(type_tutor[i], r)
                        - sum(type_tutor[i] + 1, r);
                if(i_effect > effect){
                    t = i;
                    effect = i_effect;
                }
            }
            type_tutor[t]++;
            n--;
        }

        int result = 0;
        for(int i = 1; i < type + 1; i++){
            int sum = sum(type_tutor[i], map.get(i));
            if(sum != Integer.MIN_VALUE) result += sum;
        }
        return result;
    }

    public int sum(int n, List<int[]> type_reqs){
        if(type_reqs == null || type_reqs.isEmpty()) return 0;
        int wait = 0;

        PriorityQueue<Integer> tutors = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            tutors.add(0);
        }
        for(int[] req : type_reqs){
            int time = req[0];
            int duration = req[1];

            if(tutors.peek() > time){
                wait += tutors.peek() - time;
            }
            int next = Math.max(tutors.poll(), time);
            tutors.add(next + duration);
        }
        return wait;
    }
}
