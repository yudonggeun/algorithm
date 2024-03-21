package org.example.baekjoon.graph;
// https://www.acmicpc.net/problem/1050

import java.util.*;
import java.io.*;

public class B1050 {

    static Map<String, Long> minPriceList = new HashMap<>();
    static List<Expression> expressions = new LinkedList<>();
    private static Long MAX_PRICE = 1000000001L;
    private static Long NOT_SOLVED = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        // register market products
        for (int i = 0; i < n; i++) {
            input = br.readLine().trim().split(" ");
            minPriceList.put(input[0], Long.parseLong(input[1]));
        }

        // register expression products
        for (int i = 0; i < m; i++) {
            Expression expression = new Expression(br.readLine());
            expressions.add(expression);
        }

        boolean isUpdate;
        do {
            isUpdate = false;
            for (Expression expression : expressions) {
                Long price = expression.getPrice();
                String productName = expression.productName;

                // check that the price is ever minimum price
                if (minPriceList.getOrDefault(productName, NOT_SOLVED) > price) {
                    isUpdate = true;
                    minPriceList.put(productName, price);
                }
            }
        } while (isUpdate);

        Long lovePrice = minPriceList.getOrDefault("LOVE", NOT_SOLVED);
        if (lovePrice.equals(NOT_SOLVED)) {
            System.out.println(-1);
        } else {
            System.out.println(lovePrice);
        }
    }

    static class Expression {
        public String productName;
        Map<String, Integer> ingredients = new HashMap<>();

        public Expression(String input) {
            String[] buffer = input.trim().split("=");
            this.productName = buffer[0];
            buffer = buffer[1].split("\\+");
            for (String ingredientExpression : buffer) {
                int count = ingredientExpression.charAt(0) - '0';
                String ingredientName = ingredientExpression.substring(1);
                ingredients.putIfAbsent(ingredientName, 0);
                ingredients.computeIfPresent(ingredientName, (k, v) -> v + count);
            }
        }

        public Long getPrice() {
            long sumPrice = 0;
            for (String ingredient : ingredients.keySet()) {
                if (minPriceList.getOrDefault(ingredient, NOT_SOLVED).equals(NOT_SOLVED)) {
                    return NOT_SOLVED;
                }
                int count = ingredients.get(ingredient);
                long price = minPriceList.get(ingredient);
                sumPrice += count * price;
                sumPrice = Math.min(sumPrice, MAX_PRICE);
            }
            return sumPrice;
        }
    }
}
