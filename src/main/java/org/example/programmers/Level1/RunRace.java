package org.example.programmers.Level1;//https://school.programmers.co.kr/learn/courses/30/lessons/178871
import java.util.*;

class RunRace {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> playerMap = new HashMap<>();
        for(int i = 0; i < players.length; i++){
            playerMap.putIfAbsent(players[i], i);
        }
        
        for(String call : callings){
            //swap
            int index = playerMap.get(call);
            String otherPlayer = players[index-1];
            playerMap.replace(call, index-1);
            playerMap.replace(otherPlayer, index);
            
            players[index] = otherPlayer;
            players[index-1] = call;
        }
        
        return players;
    }
}