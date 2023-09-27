//https://school.programmers.co.kr/learn/courses/30/lessons/172928
import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        init(park);
        for(String route : routes){
            dog.move(route);
        }
        return dog.answer();
    }
    
    Map<Character, int[]> map = new HashMap<>();
    String[] park;
    Dog dog;
    public void init(String[] park){
        map.put('E', new int[]{0, 1});
        map.put('W', new int[]{0, -1});
        map.put('N', new int[]{-1, 0});
        map.put('S', new int[]{1, 0});
        this.park = park;
        for(int r = 0; r < park.length; r++){
            for(int c= 0; c < park[r].length(); c++){
                if(park[r].charAt(c) == 'S'){
                    dog = new Dog(r, c);
                    return;
                }
            }
        }
    }
    
    class Dog{
        int r, c;
        
        Dog(int r, int c){
            this.r = r;
            this.c = c;
        }
        
        public void move(String order){
            char direction = order.charAt(0);
            int amount = Integer.parseInt(order.substring(2));
            int[] move = map.get(direction);
            
            int i = 1;
            int nr = r, nc = c;
            for(; i <= amount; i++){
                nr = r + i * move[0];
                nc = c + i * move[1];
                if(!isValid(nr, nc)){
                    break;
                }
            }
            if(i > amount){
                this.r = nr;
                this.c = nc;
            }
        }

        public int[] answer(){
            return new int[]{r, c};
        }
    }

    public boolean isValid(int r, int c){
        try{
            return park[r].charAt(c) != 'X';
        } catch(Exception e){
            return false;
        }
    }
}