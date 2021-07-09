package programmers;
import java.util.*;
class Solution_Camouflage {
    public int solution(String[][] clothes) {
        int answer = 1;
        Hashtable<String, Integer> hash = new Hashtable<String, Integer>();
        for(String[] detail : clothes){//착용 부위에 적용가능한 옷가지 수를 확인
            if(hash.containsKey(detail[1])) { hash.replace(detail[1], hash.get(detail[1])+1);}
            else { 							  hash.put(detail[1], 1);}
        }
        for(int x : hash.values()){
            answer *= (x+1);//해당 부위에 아무것도 착용하지 않은 경우를 포함하기위해서 +1
        }
        answer--;//아무 옷도 착용하지 않은 경우의 수 제거
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
				System.out.println("테스트 "+ (i+1) + " : 성공");
			}
			else {
				System.out.println("테스트 "+ (i+1) + " : 실패");
			}
		}
	}

}
