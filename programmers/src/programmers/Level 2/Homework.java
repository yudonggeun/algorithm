//https://school.programmers.co.kr/learn/courses/30/lessons/176962
import java.util.*;

class Solution {

    Stack<Job> stack = new Stack<>();
    Queue<Job> queue = new PriorityQueue<>();

    public String[] solution(String[][] plans) {
        List<String> result = new ArrayList<>();

        for (String[] p : plans) {
            queue.add(new Job(p));
        }
        //clean code
        int pTime = queue.peek().startTime();
        while(!queue.isEmpty()){
            while(isNotChangeJob(queue.peek(), pTime)){
                Job completeJob = completeJob();
                pTime = completeJob.endTime(pTime);
                result.add(completeJob.name);
            }
            pTime = changeJob(queue.poll(), pTime);
        }

        while (!stack.isEmpty()) result.add(completeJob().name);

        return result.toArray(new String[0]);
    }

    public boolean isNotChangeJob(Job nextJob, int pTime){
        if(stack.isEmpty()) return false;
        Job pJob = stack.peek();
        return pJob.endTime(pTime) <= nextJob.startTime();
    }

    public Job completeJob(){
        return stack.pop();
    }

    public int changeJob(Job newJob, int pTime){
        //기존의 작업을 최대한 진행시킨다.
        if(!stack.isEmpty()){
            Job pJob = stack.peek();
            pJob.duration -= newJob.startTime() - pTime;
        }
        stack.add(newJob);
        return newJob.startTime();
    }

    class Job implements Comparable<Job> {
        int duration;
        int time;
        String name;

        Job(String[] data) {
            name = data[0];
            StringTokenizer st = new StringTokenizer(data[1], ":");
            time = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
            duration = Integer.parseInt(data[2]);
        }

        @Override
        public int compareTo(Job j) {
            return time - j.time;
        }

        public int endTime(int parentTime) {
            return parentTime + duration;
        }

        public int startTime() {
            return time;
        }
    }
}