//https://school.programmers.co.kr/learn/courses/30/lessons/150370
import java.time.*;
import java.time.format.*;
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new LinkedList<>();
        LocalDate deadLine = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        Map<String, Integer> map = new HashMap<>();
        for(String t : terms){
            StringTokenizer st = new StringTokenizer(t);
            map.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        for(int i = 0; i< privacies.length; i++){
            String privacy = privacies[i];
            StringTokenizer st = new StringTokenizer(privacy);
            LocalDate time = LocalDate.parse(st.nextToken(), DateTimeFormatter.ofPattern("yyyy.MM.dd")).plusMonths(map.get(st.nextToken()));
            if(deadLine.compareTo(time) >= 0){
                result.add(i+1);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}