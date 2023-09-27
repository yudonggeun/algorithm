//https://school.programmers.co.kr/learn/courses/30/lessons/150368?language=java
import java.util.LinkedList;
import java.util.List;

class Solution {

    int[][] users;
    List<Policy> policies = new LinkedList<>();
    int join = 0, price = 0;

    public int[] solution(int[][] users, int[] emoticons) {

        this.users = users;
        dfs(emoticons, 0);

        int[] answer = {join, price};
        return answer;
    }

    public void dfs(int[] emotions, int depth) {
        if (policies.size() == emotions.length) {
            int[] result = getResult();
            int resultJoin = result[0];
            int resultPrice = result[1];

            if (join < resultJoin) {
                join = resultJoin;
                price = resultPrice;
            } else if (join == resultJoin) {
                price = Math.max(price, resultPrice);
            }
            return;
        }

        for (int discount = 10; discount <= 40; discount += 10) {
            policies.add(new Policy(emotions[depth], discount));
            dfs(emotions, depth + 1);
            policies.remove(policies.size() - 1);
        }
    }

    public int[] getResult() {
        int joinUser = 0;
        int totalPrice = 0;

        for (int[] user : users) {
            int idealDiscount = user[0];
            int maxPrice = user[1];

            int price = 0;

            for (Policy policy : policies)
                if (policy.discount >= idealDiscount)
                    price += policy.getDiscountPrice();

            if (maxPrice <= price) joinUser++;
            else totalPrice += price;
        }

        return new int[]{joinUser, totalPrice};
    }

    class Policy {
        int price, discount;

        public Policy(int price, int discount) {
            this.price = price;
            this.discount = discount;
        }

        int getDiscountPrice() {
            return price - (price * discount / 100);
        }
    }
}