package org.example.programmers.level4;

public class FourthLevelTreble {
    public int solution(int n) {

        int s = (int) (Math.log(n) / Math.log(3));

        return count(n - 2, s, s * 2 - 2, 0, 2);
    }

    private int count(int n, int s, int p, int ns, int np) {

        int a = 0, b = 0;

        if (np < ns * 2) return 0;
        if (n < 3) return 0;
        if (n == 3 && s == 1 && p == 0) {
            return 1;
        }

        if (p > 0) {
            a = count(n - 1, s, p - 1, ns, np + 1);
        }
        if (n % 3 == 0 && s > 0) {
            b = count(n / 3, s - 1, p, ns + 1, np);
        }
        return a + b;
    }
}
