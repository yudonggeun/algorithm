package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/131701
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class NumberOfConsecutiveSubsequence  {

    int[] elements;

    public int solution(int[] elements) {

        this.elements = elements;

        Set<Integer> set = new HashSet<>();

        List<Case> list = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            list.add(new Case(i));
        }

        for (int i = 0; i < elements.length; i++) {
            for (Case object : list) {
                set.add(object.getSum());
                object.sizeUp();
            }
        }

        return set.size();
    }

    public int getValue(int index) {
        return elements[index];
    }

    public int getNextIndex(int index){
        if(++index >= elements.length) return 0;
        return index;
    }

    class Case {
        int end;
        int sum;

        public Case(int first) {
            this.end = first;
            this.sum = getValue(first);
        }

        public void sizeUp() {
            this.end = getNextIndex(this.end);
            this.sum += getValue(this.end);
        }

        public int getSum(){
            return this.sum;
        }
    }
}