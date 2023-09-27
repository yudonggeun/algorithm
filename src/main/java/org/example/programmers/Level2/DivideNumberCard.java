package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/135807
import java.util.*;

class DivideNumberCard  {

    List<Integer> priorSet = getPrior();

    public List<Integer> getPrior() {

        int max = 100_000_000;
        boolean[] nums = new boolean[max + 1];

        nums[0] = true;
        nums[1] = true;
        for (int i = 2; i < nums.length; i++) {
            if (!nums[i]) {
                for (int j = i * 2; j < nums.length; j += i) {
                    nums[j] = true;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (!nums[i]) result.add(i);
        }

        return result;
    }

    public int solution(int[] arrayA, int[] arrayB) {

        Node a = new Node(arrayA[0]);
        Node b = new Node(arrayB[0]);

        for (int num : arrayA) a.add(num);
        for (int num : arrayB) b.add(num);

        long aNum = a.getNum();
        long bNum = b.getNum();

        boolean aPossible = isPossible(arrayB, aNum);
        boolean bPossible = isPossible(arrayA, bNum);

        if (aPossible && bPossible) return (int) Math.max(aNum, bNum);
        if (aPossible) return (int) aNum;
        if (bPossible) return (int) bNum;

        return 0;
    }

    private boolean isPossible(int[] array, long target) {
        boolean possible = true;
        if (target == 0) return false;
        for (int num : array) {
            if (num % target == 0) {
                possible = false;
                break;
            }
        }
        return possible;
    }

    class Node {
        Map<Integer, Integer> info;

        public Node(int num) {
            //소수 분해하여 그 정보를 info에 저장
            info = getPriorSet(num);
        }

        public void add(int num) {
            Set<Integer> keySet = new HashSet<>(info.keySet());
            for (Integer key : keySet) {
                if (num % key != 0) info.remove(key);
                else {
                    int count = 0;
                    while (num % key == 0){
                        num = num / key;
                        count++;
                    }
                    int finalCount = count;
                    info.compute(key, (k, v) -> Math.min(v, finalCount));
                }
            }
        }

        public Map<Integer, Integer> getPriorSet(int num) {

            Map<Integer, Integer> result = new HashMap<>();

            for (Integer i : priorSet) {
                if (num < i) break;
                while (num % i == 0) {
                    result.putIfAbsent(i, 0);
                    result.computeIfPresent(i, (key, value) -> value + 1);
                    num = num / i;
                }
            }

            return result;
        }

        public long getNum() {
            long result = 1;
            for (Integer key : info.keySet()) result *= Math.pow(key, info.get(key));
            if (result == 1) result = 0;
            return result;
        }
    }
}