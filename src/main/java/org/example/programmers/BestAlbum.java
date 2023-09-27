package org.example.programmers;

import java.util.*;
class Solution_BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        Hashtable<String, Integer> popular = new Hashtable<String, Integer>();
        Hashtable<String, Vector<Integer>> hashArray = new Hashtable<String, Vector<Integer>>();
        //�Է� �� �ؽ� �Է�
        for(int i = 0; i< plays.length; i++){
            if(popular.containsKey(genres[i])){
                popular.replace(genres[i], popular.get(genres[i])+plays[i]);
                hashArray.get(genres[i]).add(i);
            }
            else{
                popular.put(genres[i], plays[i]);
                Vector<Integer> temp = new Vector<Integer>();
                temp.add(i);
                hashArray.put(genres[i], temp);
            }
        }
        //����
        for(Vector<Integer> temp : hashArray.values()){
            Collections.sort(temp, new Comparator<Integer>() {
            	public int compare(Integer a, Integer b) {
            		return plays[b] - plays[a];
            	}
            });
        }
        Object[] genreSet = popular.keySet().toArray();
        Arrays.sort(genreSet, new Comparator<Object>(){
           public int compare(Object a, Object b){
               return popular.get((String)b) - popular.get((String)a);
           }
        });
        //����
        Vector<Integer> answer = new Vector<Integer>();
        for(Object genre : genreSet){
            Vector<Integer> temp = hashArray.get((String)genre);
            answer.add(temp.get(0));
            if(temp.size() > 1) answer.add(temp.get(1));
        }
        int[] fin_answer = new int[answer.size()];
        for(int i = 0; i< answer.size(); i++) fin_answer[i] = answer.get(i);
        return fin_answer;
    }
}
public class BestAlbum {
	static String[][] testcase1 = {
				{"classic", "pop", "classic", "classic", "pop"}
		};
	static int[][] testcase2 = {
				{500, 600, 150, 800, 2500}
		};
	static int[][] answer = {
				{4, 1, 3, 0}
		};
	public static boolean isEquals(int[] a, int[] b) {
		boolean answer = true;
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) answer = false;
		}
		return answer;
	}

	public static void main(String[] args) {
		Solution_BestAlbum s = new Solution_BestAlbum();
		for(int i = 0; i< answer.length; i++) {
			if(isEquals(s.solution(testcase1[i], testcase2[i]), answer[i])){
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
			else {
				System.out.println("�׽�Ʈ "+ (i+1) + " : ����");
			}
		}
	}

}
