package org.example.programmers.Level3;

//https://school.programmers.co.kr/learn/courses/30/lessons/77886
class Move110{
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            answer[i] = getString(s[i]);
        }

        return answer;
    }

    private String getString(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder plus = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c);
            if (sb.length() >= 3 && sb.charAt(sb.length() - 3) == '1' && sb.charAt(sb.length() - 2) == '1' && sb.charAt(sb.length() - 1) == '0') {
                sb.delete(sb.length() - 3, sb.length());
                plus.append("110");
            }
        }

        if (plus.length() > 0) {
            int index = sb.lastIndexOf("0");
            if (index != -1) {
                sb.insert(index + 1, plus);
            } else {
                sb.insert(0, plus);
            }
        }
        return sb.toString();
    }
}