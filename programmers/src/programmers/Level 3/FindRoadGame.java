//https://school.programmers.co.kr/learn/courses/30/lessons/42892
import java.util.*;

class Solution {
    public int[][] solution(int[][] nodeinfo) {
        Node root = null;
        Map<int[], Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            indexMap.put(nodeinfo[i], i + 1);
        }

        Arrays.sort(nodeinfo, Comparator.comparingInt(o -> -((int[]) o)[1])
                .thenComparingInt(o -> ((int[]) o)[0]));

        for (int[] info : nodeinfo) {
            int index = indexMap.get(info);
            if (root == null) root = new Node(info[0], index);
            else root.add(info[0], index);
        }

        List<Integer> preOrderList = new LinkedList<>();
        List<Integer> postOrderList = new LinkedList<>();

        root.preOrder(preOrderList);
        root.postOrder(postOrderList);

        return new int[][]{
                preOrderList.stream().mapToInt(i -> i).toArray(),
                postOrderList.stream().mapToInt(i -> i).toArray(),
        };
    }

    class Node {
        int num;
        int index;
        Node left, right;

        Node(int num, int index) {
            this.num = num;
            this.index = index;
        }

        public void preOrder(List<Integer> budget) {
            budget.add(index);
            if (left != null) left.preOrder(budget);
            if (right != null) right.preOrder(budget);
        }

        public void postOrder(List<Integer> budget) {
            if (left != null) left.postOrder(budget);
            if (right != null) right.postOrder(budget);
            budget.add(index);
        }

        public void add(int num, int index) {
            if (this.num > num) addLeft(num, index);
            else addRight(num, index);
        }

        public void addLeft(int num, int index) {
            if (left == null) left = new Node(num, index);
            else left.add(num, index);
        }

        public void addRight(int num, int index) {
            if (right == null) right = new Node(num, index);
            else right.add(num, index);
        }
    }
}