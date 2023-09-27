//https://school.programmers.co.kr/learn/courses/30/lessons/12945
class Solution {
    public int solution(int n) {
        if(n < 2) return n;
        long a = 0, b=1;
        int index = 1;
        while(true){
            index++;
            long target = (a + b) % 1234567;
            if(index == n){
                return (int) target;
            }
            a = b;
            b = target;
        }
    }
}