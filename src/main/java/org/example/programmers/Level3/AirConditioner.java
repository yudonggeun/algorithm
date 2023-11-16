package org.example.programmers.Level3;// https://school.programmers.co.kr/learn/courses/30/lessons/214289
import java.util.*;

class AirConditioner {
    
    int outTem;
    int t1, t2;
    
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        outTem = temperature + 10;
        this.t1 = t1 + 10;
        this.t2 = t2 + 10;
        int[][] dp = new int[51][onboard.length];
        int INF = Integer.MAX_VALUE - 1000000;
        for(int[] d : dp) Arrays.fill(d, INF);
        dp[outTem][0] = 0;
        
        for(int time = 0; time < onboard.length - 1; time++){
        	for(int tem = 0; tem < 51; tem++){
                if(dp[tem][time] == INF) continue;
                int runPlusTem = runPlus(tem);
                int runMinusTem = runMinus(tem);
                int runTem = run(tem);
                int offTem = off(tem);
                
                int onBoard = onboard[time + 1];
                
                if(isValid(runPlusTem, onBoard)) dp[runPlusTem][time + 1] = Math.min(dp[runPlusTem][time + 1], dp[tem][time] + a);
                if(isValid(runMinusTem, onBoard)) dp[runMinusTem][time + 1] = Math.min(dp[runMinusTem][time + 1], dp[tem][time] + a);
                if(isValid(runTem, onBoard)) dp[runTem][time + 1] = Math.min(dp[runTem][time + 1], dp[tem][time] + b);
                if(isValid(offTem, onBoard)) dp[offTem][time + 1] = Math.min(dp[offTem][time + 1], dp[tem][time]);
            }
        }
        
        
        int result = Integer.MAX_VALUE;
        int lastIndex = onboard.length -1;
        
        for(int tem = 0; tem < 51; tem++){
            result = Math.min(result, dp[tem][lastIndex]);
        }
        return result;
    }
    
    public boolean isValid(int tem, int onBoard){
        if(onBoard == 0) return true;
        return t1 <= tem && tem <= t2;
    }
    
    public int runPlus(int tem){
        if(tem == 50) return tem;
        return tem + 1;
    }
    
    public int run(int tem){
        return tem;
    }
    
    public int runMinus(int tem){
        if(tem == 0) return tem;
        return tem - 1;
    }
    
    public int off(int tem){
        if(tem == outTem) return tem;
        if(tem > outTem) return tem -1;
        return tem + 1;
    }
}