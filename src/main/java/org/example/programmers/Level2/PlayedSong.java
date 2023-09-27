package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/17683
import java.util.*;

class PlayedSong {
    public String solution(String m, String[] musicinfos) {
        Queue<Music> candidates = new PriorityQueue<>((o1, o2) -> o2.playtime() - o1.playtime());
        m = change(m);
        
        for(String input : musicinfos){
            Music music = new Music(input);
            if(music.match(m)){
                candidates.add(music);
            }
        }
        if(candidates.isEmpty()) return "(None)";
        return candidates.peek().name;
    }
    
    public String change(String s){
        char[] array = s.toCharArray();
        for(int i = 1; i < array.length; i++)
            if(array[i] == '#')
                array[i-1] += 'a' - 'A';
        return String.valueOf(array).replaceAll("#", "");
    }
    
    class Music{
        String origin;
        String full;
        String name;
        
        Music(String input){
            String[] split = input.split(",");
            String[] st = split[0].split(":");
            String[] et = split[1].split(":");
            this.name = split[2];
            this.origin = change(split[3]);
            this.full = "";
            int hourDiff = Integer.parseInt(et[0]) - Integer.parseInt(st[0]); 
            int minDiff = Integer.parseInt(et[1]) - Integer.parseInt(st[1]); 
            int diff = hourDiff * 60 + minDiff;
            for(int i = 0; i< diff / this.origin.length(); i++){
                this.full += this.origin;
            }
            this.full += this.origin.substring(0, diff % this.origin.length());
        }
        
        public boolean match(String melody){
            return full.contains(melody);
        }
        
        public int playtime(){
            return this.full.length();
        }
    }
}