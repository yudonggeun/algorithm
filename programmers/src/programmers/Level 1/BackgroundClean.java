//https://school.programmers.co.kr/learn/courses/30/lessons/161990
class Solution {
    public int[] solution(String[] wallpaper) {
        w = wallpaper;
        rLen = w.length;
        cLen = w[0].length();
        int[] answer = {startR(), startC(), endR()+1, endC()+1};
        return answer;
    }
    
    int rLen, cLen;
    String[] w;
    public char get(int r, int c){
        return w[r].charAt(c);
    }
    
    public int startR(){
        for(int r = 0; r < rLen; r++){
            for(int c = 0; c < cLen; c++){
                if(get(r, c) == '#'){
                    return r;
                }
            }
        }
        return rLen-1;
    }
    
    public int endR(){
        for(int r = rLen-1; r > -1; r--){
            for(int c = 0; c < cLen; c++){
                if(get(r, c) == '#'){
                    return r;
                }
            }
        }
        return rLen-1;
    }
    
    public int startC(){
        for(int c = 0; c < cLen; c++){
            for(int r = 0; r < rLen ;r++){
                if(get(r, c) == '#'){
                    return c;
                }
            }
        }
        return cLen -1;
    }
    
    public int endC(){
        for(int c = cLen -1; c > -1; c--){
            for(int r = 0; r < rLen ;r++){
                if(get(r, c) == '#'){
                    return c;
                }
            }
        }
        return cLen -1;
    }
}