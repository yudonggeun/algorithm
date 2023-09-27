//https://school.programmers.co.kr/learn/courses/30/lessons/42747
class Solution {
    public int solution(int[] citations) {
        int[] count = new int[10001];

        for (int citation : citations){
            count[citation]++;
        }

        for(int i = count.length -1; i > 0; i--){
            count[i-1] =  count[i-1] + count[i];
        }

        for(int i = count.length -1; i > -1; i--){
            if(count[i] >= i){
                return i;
            }
        }
        return 0;
    }
}