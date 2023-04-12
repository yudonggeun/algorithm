//https://school.programmers.co.kr/learn/courses/30/lessons/17686
import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<File> list = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            list.add(new File(files[i], i));
        }

        Collections.sort(list);
        String[] result = new String[files.length];

        for (int i = 0; i < files.length; i++) {
            result[i] = list.get(i).origin;
        }
        return result;
    }

    public static void main(String[] args) {
        String origin = "teset11";
        String tem = origin.toLowerCase().replaceFirst("[0-9]\\D.*", "");
        System.out.println(tem);
        origin = origin.substring(0, tem.length() + 1);
        System.out.println(origin);
    }

    class File implements Comparable<File> {
        String head;
        int index;
        int number;
        String origin;

        File(String origin, int index) {
            this.origin = origin;
            this.index = index;

            origin = origin.toLowerCase();
            int endIndex = origin.replaceFirst("[0-9]\\D.*", "").length() + 1;
            origin = origin.substring(0, endIndex);
            this.head = origin.replaceAll("[0-9]", "");
            this.number = Integer.parseInt(origin.replaceAll(this.head, ""));

            System.out.println(this.origin + " : " + head + ", " + number + ", " + index);
        }

        @Override
        public int compareTo(File file) {
            if (!head.equals(file.head)) return Objects.compare(this.head, file.head, Comparator.naturalOrder());
            if (number != file.number) return this.number - file.number;
            return this.index - file.index;
        }
    }

}