package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/131127
import java.util.HashMap;
import java.util.Map;

class DiscountEvent  {
    public int solution(String[] want, int[] number, String[] discount) {

        Info 정현 = new Info();
        Info mart = new Info();

        for (int i = 0; i < want.length; i++) 정현.add(want[i], number[i]);
        for (int i = 0; i < 9; i++) mart.add(discount[i]);

        int count = 0;

        for (int i = 9; i < discount.length; i++) {
            mart.add(discount[i]);
            if(isMatch(정현, mart)) count++;
            mart.remove(discount[i - 9]);
        }

        return count;
    }

    public boolean isMatch(Info consumer, Info provider){
        for (String product : consumer.info.keySet())
            if (consumer.getCount(product) > provider.getCount(product)) return false;
        return true;
    }
    class Info {
        Map<String, Integer> info = new HashMap<>();

        public void add(String product) {
            info.putIfAbsent(product, 0);
            info.computeIfPresent(product, (k, v) -> v + 1);
        }

        public void add(String product, Integer count) {
            info.putIfAbsent(product, count);
        }

        public Integer getCount(String product) {
            return info.getOrDefault(product, 0);
        }

        public void remove(String product) {
            info.computeIfPresent(product, (k, v) -> Math.max(0, v - 1));
        }
    }
}