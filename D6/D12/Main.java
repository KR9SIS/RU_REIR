import java.util.Arrays;

public class Main {
    // WARN: DOES NOT WORK
    public static void main(String[] args) {
        /*
         * Problem Statement
         * A reporter wants to find out what point difference occurs the most frequently
         * in games in the football league. Namely, if one team scores 68 and the other
         * 75, the point difference is 7. He has the outcome of all n games in the
         * league (stored in an array) and needs your help determining the most common
         * point difference. Describe an Θ(n log n) algorithm to do so.
         * For instance, if the games went: 75 vs. 67, 95 vs. 80, 92 vs. 100, then 8 is
         * the most common point difference.
         */
        Kattio io = new Kattio(System.in, System.out);
        int[] a1 = new int[] { 67, 80, 100, 80, 80, 75, 75 };
        int[] a2 = new int[] { 75, 95, 92, 84, 84, 60, 60 };
        int[] diffs = new int[a2.length];

        for (int i = 0; i < a1.length; i++) {
            int diff = Math.abs(a1[i] - a2[i]);
            io.printf("%d - %d = %d\n", a1[i], a2[i], diff);
            diffs[i] = diff;
        }

        Arrays.sort(diffs);
        io.dumpIntArr(diffs);

        int maxCount = 0, currCount = 0, maxIdx = 0, i = 0;
        while (i < diffs.length) {
            if ((diffs[i - 1] != diffs[i]) && (currCount > maxCount)) {
                maxCount = currCount;
                maxIdx = i - 1;
                currCount = 0;
            } else {
                currCount++;
            }
        }

        io.println(diffs[maxIdx]);
        io.close();
    }
}
