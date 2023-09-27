package org.example.programmers;
import java.util.*;
class Solution_PhoneNumberList {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashSet<String> hash = new HashSet<String>();
        //���� ������ ����
        Arrays.sort(phone_book, new Comparator<String>(){
            public int compare(String a, String b){
                return b.length() - a.length();
            }
        });
        int minLen = phone_book[phone_book.length-1].length();//��ȭ��ȣ�ο��� �����ϴ� ���� ���� ����
        for(String number : phone_book){
            if(hash.contains(number)){ answer = false;}/*���ξ���*/
            else{//���ξ �ƴ϶�� �ش� ��ȣ�� ���ξ� ������ ��ҵ��� �ؽÿ� ����
                for(int i = minLen; i<= number.length(); i++){
                    hash.add(number.substring(0, i)); }
            }
        }
        return answer;
    }
}
public class PhoneNumberList {
	static String [][] phone_book = {
			{"119", "97674223", "1195524421"},
			{"123","456","789"},
			{"12","123","1235","567","88"}
	};
	static boolean [] answer = {
			false, true, false
	};
	public static void main(String[] args) {
		Solution_PhoneNumberList s = new Solution_PhoneNumberList();
		for(int i = 0; i < answer.length; i++) {
			if(s.solution(phone_book[i]) == answer[i]) {
				System.out.println("�׽�Ʈ " + (i+1) 
						+ " ����");
			}
			else {
				System.out.println("�׽�Ʈ " + (i+1) + " ����");
			}
		}
	}

}
