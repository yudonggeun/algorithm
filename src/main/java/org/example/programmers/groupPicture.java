package org.example.programmers;
import java.util.*;
class Solution_groupPicture {
    String [] data;
    char [] set = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    Hashtable<Character, Integer> hash;
    int count;
    public void makeCase(int depth, boolean[] left){
        if(depth == 8){
            if(check()) count++;
        }
        else{
            for(int i = 0; i< 8; i++){
                if(left[i]){
                    hash.replace(set[depth], i);
                    left[i] = false;
                    makeCase(depth+1, left);
                    left[i] = true;
                }
            }
        }
    }
    public boolean check(){
        for(String condition : data){
            char a = condition.charAt(0);
            char b = condition.charAt(2);
            char simbol = condition.charAt(3);
            int distance = Integer.parseInt(condition.substring(4));
            switch(simbol){
                case '=':
                    if(Math.abs(hash.get(a) - hash.get(b)) != distance +1) return false;
                    break;
                case '<':
                    if(Math.abs(hash.get(a) - hash.get(b)) >= distance +1) return false;
                    break;
                case '>':
                    if(Math.abs(hash.get(a) - hash.get(b)) <= distance +1) return false;
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
    public int solution(int n, String[] data) {
        this.count = 0;
        this.data = data;
        hash = new Hashtable<Character, Integer>();
        hash.put('A', 0);        hash.put('C', 1);
        hash.put('F', 2);        hash.put('J', 3);
        hash.put('M', 4);        hash.put('N', 5);   
        hash.put('R', 6);        hash.put('T', 7);
        makeCase(0, new boolean[]{true, true, true, true, true, true, true, true});
        return count;
    }
}
public class groupPicture {
	static String[][] testcase = {
			{"N~F=0", "R~T>2"},
			{"M~C<2", "C~M>1"}
	};
	static int [] answer = {
			3648, 0
	};
	public static void main(String[] args) {
		Solution_groupPicture s = new Solution_groupPicture();
		for(int i = 0; i< answer.length; i++) {
			if(s.solution(testcase[i].length, testcase[i]) == answer[i]) {
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
			else {
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
		}

	}

}
