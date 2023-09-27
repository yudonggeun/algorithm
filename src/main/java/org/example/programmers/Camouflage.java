package org.example.programmers;
import java.util.*;
class Solution_Camouflage {
    public int solution(String[][] clothes) {
        int answer = 1;
        Hashtable<String, Integer> hash = new Hashtable<String, Integer>();
        for(String[] detail : clothes){//���� ������ ���밡���� �ʰ��� ���� Ȯ��
            if(hash.containsKey(detail[1])) { hash.replace(detail[1], hash.get(detail[1])+1);}
            else { 							  hash.put(detail[1], 1);}
        }
        for(int x : hash.values()){
            answer *= (x+1);//�ش� ������ �ƹ��͵� �������� ���� ��츦 �����ϱ����ؼ� +1
        }
        answer--;//�ƹ� �ʵ� �������� ���� ����� �� ����
        return answer;
    }
}
public class Camouflage {
	static String[][][] testcase = {
			{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}},
			{{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}}
	};
	static int[] answer = {5, 3};
	public static void main(String[] args) {
		Solution_Camouflage s = new Solution_Camouflage();
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
