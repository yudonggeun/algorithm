//https://school.programmers.co.kr/learn/courses/30/lessons/148653
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int storey) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(storey, 0));

        int answer = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if(answer < node.count) continue;
            if(node.storey == 0){
                answer = Math.min(node.count, answer);
                continue;
            }

            int num = node.storey;
            int count = node.count;
            int div = num % 10;
            num = num /10;

            if(div < 5){
                count += div;
                queue.add(new Node(num, count));
            } else if(div == 5){
                count += 5;
                queue.add(new Node(num + 1, count));
                queue.add(new Node(num, count));
            } else {
                count += 10 - div;
                num ++;
                queue.add(new Node(num, count));
            }
        }

        return answer;
    }

    class Node {
        int storey, count;
        public Node(int storey, int count) {
            this.storey = storey;
            this.count = count;
        }
    }
}