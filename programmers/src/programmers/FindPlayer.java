package programmers;
import java.util.*;

class Solution_FindPlayer {
    public String solution(String[] participant, String[] completion) {
        Hashtable<String, Integer> hash = new Hashtable<String, Integer>();
        String answer = "none";
        //������ �ؽ� �߰�
        for(String name : completion){
            if(hash.containsKey(name)){ hash.replace(name, hash.get(name)+1);}
            else					  { hash.put(name, 1); }
        }
        //������ ��ܿ��� ������ ����� �� ����
        for(String name : participant){
            if(hash.containsKey(name)){
                int count = hash.get(name);
                if(count == 1){ hash.remove(name); }
                else{		    hash.replace(name, count-1);}
            }
            else{
                answer = name;
                break;
            }
        }        
        return answer;
    }
}
public class FindPlayer {
	static String[][] participant = {
			{"leo", "kiki", "eden"},
			{"marina", "josipa", "nikola", "vinko", "filipa"},
			{"mislav", "stanko", "mislav", "ana"}
	};
	static String[][] completion = {
			{"eden", "kiki"},
			{"josipa", "filipa", "marina", "nikola"},
			{"stanko", "ana", "mislav"}
	};
	static String[] answer = {
			"leo", "vinko", "mislav"
	};
	public static void main(String[] args) {
		Solution_FindPlayer s = new Solution_FindPlayer();
		for(int i = 0; i< answer.length; i++) {
			if(s.solution(participant[i], completion[i]).equals(answer[i])) {
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
			else {
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
		}
	}
}
