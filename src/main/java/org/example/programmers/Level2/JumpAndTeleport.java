package org.example.programmers.Level2;//https://school.programmers.co.kr/learn/courses/30/lessons/12980
import java.util.*;

public class JumpAndTeleport  {
    public int solution(int n) {
        return Integer.toString(n, 2).replace("0", "").length();
    }
}