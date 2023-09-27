package org.example.programmers.Level1;//https://school.programmers.co.kr/learn/courses/30/lessons/159994

class CardSet {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int a1 = 0, a2 = 0;
        
        for(String target : goal){
            if(a1 < cards1.length && target.equals(cards1[a1])){
                a1++;
            } else if(a2 < cards2.length && target.equals(cards2[a2])){
                a2++;
            } else {
                return "No";
            }
        }
        
        return "Yes";
    }
}