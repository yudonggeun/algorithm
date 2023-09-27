//https://school.programmers.co.kr/learn/courses/30/lessons/17681
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        
        String[] answer = new String[n];
        String form = "%" + n + "s";
        for(int i = 0; i < arr1.length; i++){
            arr1[i] = arr1[i] | arr2[i];
            String result = Integer.toString(arr1[i], 2);
            result = result.replace("1", "#");
            result = result.replace("0", " ");
            result = String.format(form, result);
            answer[i] = result;
        }
        
        return answer;
    }
}