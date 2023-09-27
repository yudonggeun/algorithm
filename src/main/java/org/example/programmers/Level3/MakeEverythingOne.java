package org.example.programmers.Level3;//https://school.programmers.co.kr/learn/courses/30/lessons/76503
import java.util.*;

class MakeEverythingOne{
    public long solution(int[] a, int[][] edges) {
        init(a, edges);

        long answer = 0;
        while (!leaves.isEmpty()) {
            Integer leaf = leaves.poll();
            Integer parent = getParent(leaf);

            if (leaf == null || parent == null) continue;
            answer += move(leaf, parent);
            removePath(leaf, parent);
        }
        return map.keySet().isEmpty() ? answer : -1;
    }

    public long move(int leaf, int parent) {
        long result = Math.abs(w[leaf]);
        w[parent] += w[leaf];
        w[leaf] = 0;
        return result;
    }

    public void removePath(Integer a, Integer b) {
        removeNode(a, b);
        removeNode(b, a);
    }

    public void removeNode(Integer a, Integer b) {
        if (map.get(a) == null) return;
        map.get(a).remove(b);
        if (map.get(a).isEmpty() && w[a] == 0) map.remove(a);
        else if (map.get(a).size() == 1) leaves.add(a);
    }

    public Integer getParent(Integer leaf) {
        if (leaf == null || map.get(leaf) == null) return null;
        Set<Integer> set = map.get(leaf);
        Integer result = null;
        for (Integer integer : set) result = integer;
        return result;
    }

    Map<Integer, Set<Integer>> map = new HashMap<>();
    Deque<Integer> leaves = new LinkedList<>();
    long[] w;

    public void init(int[] a, int[][] edges) {
        w = new long[a.length];
        for (int i = 0; i < a.length; i++) w[i] = a[i];
        for (int[] edge : edges) {
            int t = edge[0], f = edge[1];

            map.putIfAbsent(t, new HashSet<>());
            map.putIfAbsent(f, new HashSet<>());

            map.get(t).add(f);
            map.get(f).add(t);
        }

        for (Integer node : map.keySet())
            if (map.get(node).size() == 1) leaves.add(node);
    }
}