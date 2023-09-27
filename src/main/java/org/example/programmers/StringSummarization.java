package org.example.programmers;
class Solution_StringSummarization {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;
        if(s.length() == 1) answer = 1;//�ϳ��� �ܾ�� �������� �ʴ� ���
        for(int len = 1; len<= s.length()/2; len++){
            
            String [] array = stringSplit(s, len);         
            
            int count = 1;//�ߺ��� ī����
            int index = 0;//������ �Ǵ� index
            String result = "";//������
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
        	stringArray[i] = s.substring(i*len, i*len+len);//���̰� len�� �迭�� ä���
        
        stringArray[size] = s.substring(len*size);//������ len ������ ���ڿ� ���� ����
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
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
			else {
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
		}
	}

}
