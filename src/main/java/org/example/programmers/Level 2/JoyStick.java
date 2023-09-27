//https://school.programmers.co.kr/learn/courses/30/lessons/42860#
class Solution {

    public static void main(String[] args) {
        String a =
                "input:LABLPAJM / answer:61\n" +
                        "input:GTAASKKAE / answer:52\n" +
                        "input:BMOABA / answer:30\n" +
                        "input:LAABAA / answer:15\n" +
                        "input:AAAAAAAAJAAAA / answer:14\n" +
                        "input:SAAAAAARRM / answer:41\n" +
                        "input:RABAMATAWADLAFAVAAE / answer:78\n" +
                        "input:XAAAAAABA / answer:6\n" +
                        "input:AYOZAAVADAY / answer:35\n" +
                        "input:AAFEASAAVA / answer:30\n" +
                        "input:UAGAAASAAFAFXZA / answer:47\n" +
                        "input:AAAAZAATAEA / answer:19\n" +
                        "input:AACALATLAHABAA / answer:50\n" +
                        "input:FAWJAAAFV / answer:35\n" +
                        "input:AACAVAAPSAAOAA / answer:49\n" +
                        "input:AKAAWAKX / answer:33\n" +
                        "input:LOAAAHAJAAFAEBAWO / answer:79\n" +
                        "input:AWAWVAQVAAA / answer:35\n" +
                        "input:RCETAAAAVUEAETZAAAK / answer:75\n" +
                        "input:AAAABAAAAAAKSAIQ / answer:49\n" +
                        "input:ADASAAAUAAAPAA / answer:39\n" +
                        "input:AAAAADBAAELSPUAAAOA / answer:70\n" +
                        "input:VJAAIAFNAAAAA / answer:47\n" +
                        "input:AARUAUAAHTBJAAYS / answer:69\n" +
                        "input:IASAGITUPHE / answer:74\n" +
                        "input:AAALAAAAAA / answer:14\n" +
                        "input:AAAEASAHQAYTAAAJ / answer:60\n" +
                        "input:BAALEAAAPMAAAHSRAV / answer:83\n" +
                        "input:ASWAAATDAJAXA / answer:45\n" +
                        "input:DYAOAAAARQANAWA / answer:66\n" +
                        "input:AAIAPB / answer:24\n" +
                        "input:ABABAAAAAAABA / answer:10\n" +
                        "input:BAAAAABBB / answer:7\n" +
                        "";
        Solution solution = new Solution();
        for (String s : a.split("\n")) {
            String[] split = s.split(" / ");
            String input = split[0].replaceAll("input:", "").trim();
            int answer = Integer.parseInt(split[1].replaceAll("answer:", ""));
            int result = solution.solution(input);
            if (answer != result)
                System.out.println(input + "= answer : " + answer + " result : " + result);
        }
    }

    int target = 0;
    int answer = Integer.MAX_VALUE;
    int[] next = {1, -1};

    public int solution(String name) {

        target = 0;
        answer = Integer.MAX_VALUE;
        for (char c : name.toCharArray()) target += move(c);

        char[] array = name.toCharArray();
        array[0] = 'A';
        dfs(0, 0, move(name.charAt(0)), array);
        return answer + target;
    }

    public void dfs(int index, int move, int sum, char[] name) {

        if (name.length <= move) return;
        if (sum == target) {
            answer = Math.min(answer, move);
            return;
        }

        for (int n : next) {
            int nextIndex = getNext(index + n, name.length);
            int nextSum = sum + move(name[nextIndex]);
            char tem = name[nextIndex];
            name[nextIndex] = 'A';
            dfs(nextIndex, move + 1, nextSum, name);
            name[nextIndex] = tem;
        }
    }

    public int getNext(int index, int len) {
        if (index < 0) return len - 1;
        if (index >= len) return 0;
        return index;
    }

    public int move(char t) {
        return Math.min('Z' - t + 1, t - 'A');
    }
}