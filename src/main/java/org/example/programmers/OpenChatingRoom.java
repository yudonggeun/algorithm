package org.example.programmers;

import java.util.*;

class Record{
	String name;
	String type;
	Record(String type){
		this.type = type;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return name + "���� " + type;
	}
}
class Solution_OpenChatingRoom {
    public String[] solution(String[] record) {
        Vector<Record> r = new Vector<Record>();
        Hashtable<String, Vector<Integer>> changeHash = new Hashtable<String, Vector<Integer>>();
        Hashtable<String, String> nameHash = new Hashtable<String, String>();
        
        for(String log : record) {
        	String log_split[] = log.split(" ");
        	if(!nameHash.containsKey(log_split[1])) {//���� �̿��� �ʱ�ȭ
        		nameHash.put(log_split[1], "");
        		changeHash.put(log_split[1], new Vector<Integer>());
        	}
        	if(log_split[0].equals("Enter")) {
        		nameHash.replace(log_split[1], log_split[2]);//���̵� �̸� ���� ����
        		changeHash.get(log_split[1]).add(r.size());//�Է��ϴ� ����� �ε����� �Է�
        		Record temp = new Record("���Խ��ϴ�.");
        		r.add(temp);
        	}
        	else if(log_split[0].equals("Change")) {
        		nameHash.replace(log_split[1], log_split[2]);//���̵� �̸� ���� ����
        	}
        	else if(log_split[0].equals("Leave")) {
        		changeHash.get(log_split[1]).add(r.size());//�Է��ϴ� ����� �ε����� �Է�
        		Record temp = new Record("�������ϴ�.");
        		r.add(temp);
        	}
        	//����ȭ
        	for(int index : changeHash.get(log_split[1])) {
        		r.get(index).setName(nameHash.get(log_split[1]));
        	}
        }
        String answer[] = new String[r.size()];
        for(int i = 0; i< r.size(); i++) {
        	answer[i] = r.get(i).toString();
        	System.out.println(r.get(i));
        }
        return answer;
    }
}
public class OpenChatingRoom {
	static String testCase1 [] = {
			"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234",
			"Enter uid1234 Prodo","Change uid4567 Ryan"};
	static String answer1 [] = {
			"Prodo���� ���Խ��ϴ�.", "Ryan���� ���Խ��ϴ�.", "Prodo���� �������ϴ�.",
			"Prodo���� ���Խ��ϴ�."};
	public static void main(String[] args) {
		Solution_OpenChatingRoom s = new Solution_OpenChatingRoom();
		if(stringArrayEquals(s.solution(testCase1), answer1)) {
			System.out.println("����");
		}
		else {
			System.out.println("����");
		}
	}
	public static boolean stringArrayEquals(String[] a, String[] b) {
		if(a.length != b.length) return false;
		for(int i = 0; i< a.length; i++) {
			if(!a[i].equals(b[i])) {
				return false;
			}
		}
		return true;
	}
}
