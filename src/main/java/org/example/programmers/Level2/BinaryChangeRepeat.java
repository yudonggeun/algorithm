package org.example.programmers.Level2;

//https://school.programmers.co.kr/learn/courses/30/lessons/70129
class BinaryChangeRepeat  {
    int zero = 0;
    int count = 0;
    public int[] solution(String s) {
        while (!s.equals("1")){
            s = binaryChange(s);
            count++;
        }
        return new int[]{count, zero};
    }

    public String binaryChange(String s){
        int oneCount = 0;
        for (char c : s.toCharArray()) if (c == '1') oneCount++;
        zero += s.length() - oneCount;
        return Integer.toString(oneCount, 2);
    }
}