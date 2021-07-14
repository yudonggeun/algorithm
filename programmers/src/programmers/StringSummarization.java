package programmers;
class Solution_StringSummarization {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        if(s.length() == 1) answer = 1;//하나의 단어로 분할하지 않는 경우
        for(int len = 1; len<= s.length()/2; len++){
            
            String [] array = stringSplit(s, len);         
            
            int count = 1;//중복수 카운터
            int index = 0;//기준이 되는 index
            String result = "";//압출결과
            for(int j = 1; j < array.length; j++){
                if(array[index].equals(array[j])){
                    count++;
                }
                else{
                    if(count > 1) result += count;
                    result += array[index];
                    count = 1;
                    index = j;
                }
            }
            if(count > 1) result += count;
            result += array[index];
            
            if(answer > result.length()) {
            	answer = result.length();
            }
        }
        return answer;
    }
    public String[] stringSplit(String s, int len){
        int size = s.length()/len + (s.length()%len > 0 ? 1 : 0);
        String[] stringArray = new String[size];
        size--;
        for(int i = 0; i < size; i++)
        	stringArray[i] = s.substring(i*len, i*len+len);//길이가 len인 배열을 채우기
        
        stringArray[size] = s.substring(len*size);//나머지 len 이하의 문자열 집합 쓰기
        return stringArray;
    }
}
public class StringSummarization {
	static String[] testcase = {
			"a",
			"aabbaccc", 
			"ababcdcdababcdcd", 
			"abcabcdede", 
			"abcabcabcabcdededededede", 
			"xababcdcdababcdcd",
			"aaaaaaaaaab",
			"acacacacacacbacacacacacac"
	};
	static int [] answer = {1, 7, 9, 8, 14, 17, 4, 9};
	public static void main(String[] args) {
		Solution_StringSummarization s = new Solution_StringSummarization();
		for(int i = 0; i< answer.length; i++) {
			if(s.solution(testcase[i]) == answer[i]) {
				System.out.println("테스트 "+ (i+1) + " : 성공");
			}
			else {
				System.out.println("테스트 "+ (i+1) + " : 실패");
			}
		}
	}

}
