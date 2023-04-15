//https://school.programmers.co.kr/learn/courses/30/lessons/92334
import java.util.*;

class Solution {
    Map<String, Set<String>> reportRecord = new HashMap<>();
    Map<String, Set<String>> receiveReportRecord = new HashMap<>();
    
    public int[] solution(String[] id_list, String[] report, int k) {
        for(String input : report){
            String[] info = input.split(" ");
            String from = info[0];
            String to = info[1];
            
            reportRecord.putIfAbsent(from, new HashSet<>());
            receiveReportRecord.putIfAbsent(to, new HashSet<>());
            
            reportRecord.get(from).add(to);
            receiveReportRecord.get(to).add(from);
        }
        
        int[] answer = new int[id_list.length];
        for(int i = 0; i< answer.length; i++){
            int num = (int) reportRecord.getOrDefault(id_list[i], new HashSet<>())
                .stream()
                .filter(person -> receiveReportRecord.get(person).size() >= k)
                .count();
            answer[i] = num;
        }
        
        return answer;
    }
}