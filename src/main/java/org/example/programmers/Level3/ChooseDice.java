// https://school.programmers.co.kr/learn/courses/30/lessons/258709?language=java
package org.example.programmers.Level3;

import java.util.*;

public class ChooseDice {
    public static void main(String[] args) {
        new ChooseDice().solution(new int[10][5]);
    }

    public int[] solution(int[][] dice) {
        int lastDiceIndex = dice.length - 1;
        // step 1 : calculate n/2 cases result set
        var origin = new HashSet<Integer>();
        for (int i = 0; i <= lastDiceIndex; i++) origin.add(1 << i);

        for (int i = 1; i < dice.length / 2; i++) {
            var next = new HashSet<Integer>();
            setCases(origin, next, lastDiceIndex + 1);
            origin = next;
        }

        // step 2 : compare other set that is matching only one
        var finalStatistic = new Statistic();
        int diceIndexes = 0;
        var visitSet = new HashSet<Integer>();
        for (Integer indexes : origin) {
            if (!visitSet.contains(indexes)) {
                int reverseIndexes = reverseIndexes(indexes, dice.length);
                visitSet.add(indexes);
                visitSet.add(reverseIndexes);
                List<int[]> diceSetA = getDiceSet(dice, indexes);
                List<int[]> diceSetB = getDiceSet(dice, reverseIndexes);
                Map<Integer, Integer> resultA = getResult(diceSetA);
                Map<Integer, Integer> resultB = getResult(diceSetB);

                var statistic = getStatistic(resultA, resultB);
                if (!statistic.isWinA()) { // winner is B
                    if (finalStatistic.winRate() < statistic.loseRate()) {
                        finalStatistic = statistic.reverse();
                        diceIndexes = reverseIndexes;
                    }
                } else { // winner is A
                    if (finalStatistic.winRate() < statistic.winRate()) {
                        finalStatistic = statistic;
                        diceIndexes = indexes;
                    }
                }
            }
        }
        // step 3 : select the best combination
        int[] answer = getDiceIndexArray(diceIndexes);
        return answer;
    }

    class Statistic {
        int win = 0, lose = 0, draw = 0;

        public boolean isWinA() {
            return win > lose;
        }

        public double winRate() {
            if (win == 0 && lose == 0 && draw == 0) return 0;
            return ((double) win) / (win + lose + draw);
        }

        public double loseRate() {
            if (win == 0 && lose == 0 && draw == 0) return 0;
            return ((double) lose) / (win + lose + draw);
        }

        public Statistic reverse() {
            Statistic statistic = new Statistic();
            statistic.win = lose;
            statistic.lose = win;
            statistic.draw = draw;
            return statistic;
        }
    }

    private int[] getDiceIndexArray(int diceIndexes) {
        var result = new ArrayList<Integer>();
        for (int i = 0; i < 11; i++) {
            if ((diceIndexes & (1 << i)) != 0) {
                result.add(i + 1);
            }
        }
        Collections.sort(result);
        return result.stream().mapToInt(i -> i).toArray();
    }

    /*
    A가 이길 확률을 계산한다.
     */
    Statistic getStatistic(Map<Integer, Integer> resultA, Map<Integer, Integer> resultB) {
        var statistic = new Statistic();

        for (Integer elementA : resultA.keySet()) {
            var countA = resultA.get(elementA);
            for (Integer elementB : resultB.keySet()) {
                var countB = resultB.get(elementB);
                if (elementA > elementB) {
                    statistic.win += countA * countB;
                } else if (elementA.equals(elementB)) {
                    statistic.draw += countA * countB;
                } else {
                    statistic.lose += countA * countB;
                }
            }
        }
        return statistic;
    }

    /*
    주사위를 굴려서 나올 수 있는 모든 경우를 구한다.
     */
    Map<Integer, Integer> getResult(List<int[]> dice) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        for (int[] oneDice : dice) {
            Queue<Integer> nextQueue = new LinkedList<>();
            for (int target : oneDice) {
                for (Integer element : queue) {
                    nextQueue.add(element + target);
                }
            }
            queue = nextQueue;
        }
        var result = new HashMap<Integer, Integer>();
        while (!queue.isEmpty()) {
            Integer element = queue.poll();
            result.computeIfPresent(element, (k, v) -> v + 1);
            result.putIfAbsent(element, 1);
        }
        return result;
    }

    List<int[]> getDiceSet(int[][] dice, int indexes) {
        var result = new LinkedList<int[]>();
        for (int i = 0; i < dice.length; i++) {
            int num = 1 << i;
            if ((num & indexes) == num) {
                result.add(dice[i]);
            }
        }
        return result;
    }

    int reverseIndexes(int indexes, int maxLen) {
        int num = indexes ^ Integer.MAX_VALUE;
        return num % (1 << maxLen);
    }

    void setCases(Set<Integer> cases, Set<Integer> nextCases, int max) {
        for (Integer c : cases) {
            for (int i = 0; i < max; i++) {
                int num = 1 << i;
                if ((c & num) == 0) {
                    int next = c + num;
                    nextCases.add(next);
                }
            }
        }
    }
}
