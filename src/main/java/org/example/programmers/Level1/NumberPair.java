package org.example.programmers.Level1;

//https://school.programmers.co.kr/learn/courses/30/lessons/131128
class NumberPair {
    public String solution(String X, String Y) {
        int[] xCount = new int[10];
        int[] yCount = new int[10];

        for (char c : X.toCharArray()) xCount[c - '0']++;
        for (char c : Y.toCharArray()) yCount[c - '0']++;

        StringBuilder answer = new StringBuilder();
        for (int i = 9; i > -1; i--) {
            int count = Math.min(xCount[i], yCount[i]);
            for (int j = 0; j < count; j++) answer.append(i);
        }

        String result = answer.toString().replaceAll("^0+", "0");
        if (result.equals("")) result = "-1";
        return result;
    }
}