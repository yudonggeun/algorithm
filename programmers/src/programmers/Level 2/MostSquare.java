//https://school.programmers.co.kr/learn/courses/30/lessons/12905
import java.util.*;
class Solution
{
    public int solution(int [][]board)
    {
        for(int i = 0; i< board.length; i++){
            for(int j = 1; j < board[i].length; j++){
                board[i][j] += board[i][j-1];
            }
        }
        for(int i = 1; i< board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] += board[i-1][j];
            }
        }
        
        int left = 1, right = board.length;
        
        while(left <= right){
            int mid = (left + right) /2;
            
            if(isValid(mid, board)){
                left = mid +1;
            } else{
                right = mid -1;
            }
        }
        
        return right * right;
    }
    
    public boolean isValid(int len, int[][] array){
        int size = len * len;
        for(int r = array.length -1; r >= len-1; r--){
            for(int c = array[r].length-1; c >= len -1; c--){
                int d = sum(r, c, array); 
                int a = sum(r-len, c-len, array);
                int b = sum(r-len, c, array);
                int e = sum(r, c-len, array);
                
                int target = d - b - e + a;
                if(target == size) {
                    return true;
                }
            }
        }
        return false;
    }
    public int sum(int r, int c, int[][] array){
        try{
            return array[r][c];
        } catch(Exception e){
            return 0;
        }
    }
}