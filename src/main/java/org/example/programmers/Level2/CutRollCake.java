package org.example.programmers.Level2;

import java.util.HashMap;
import java.util.Map;

class CutRollCake  {
    public int solution(int[] topping) {

        int answer = 0;
        info person1 = new info();
        info person2 = new info();

        for (int t : topping) person1.add(t);

        for (int t : topping) {
            person1.minus(t);
            person2.add(t);

            if(person1.getTypeCount() == person2.getTypeCount()){
                answer++;
            }
        }

        return answer;
    }

    class info {
        Map<Integer, Integer> map = new HashMap<>();

        public void add(int topping) {
            map.putIfAbsent(topping, 0);
            map.computeIfPresent(topping, (type, count) -> count + 1);
        }

        public void minus(int topping) {
            map.computeIfPresent(topping, (type, count) -> count - 1);
            if(map.get(topping).equals(0)) map.remove(topping);
        }

        public int getTypeCount(){
            return map.keySet().size();
        }
    }
}