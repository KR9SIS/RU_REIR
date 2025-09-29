import java.util.Arrays;

/**
 * Computes the minimal words per minute needed so that the total time penalty
 * (sum of completion times, each being floor(cumulative_words_typed / wpm))
 * is strictly less than the top team's penalty T.
 *
 * Constraints:
 * 1 <= n <= 100000
 * 1 <= T <= 1e18
 * 0 <= w_i <= 1e9
 * Sum of all w_i > 0
 */
public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        TimePenalty topPenalty = new TimePenalty();
        topPenalty.addFromSec(io.getLong());

        long[] problems = new long[n];
        for (int i = 0; i < n; i++) {
            problems[i] = io.getInt();
        }
        Arrays.sort(problems);

        long lo = 0;
        long hi = Long.MAX_VALUE;

        while (lo < hi) {
            // >>> 1 is a fast way to do integer division by 2
            long wpm = lo + ((hi - lo) >>> 1);
            if (penaltyLessThan(problems, wpm, topPenalty, io)) {
                // penalty(mid) < topPenalty, try smaller wpm
                hi = wpm;
            } else {
                // penalty(mid) >= topPenalty, need higher speed
                lo = wpm + 1;
            }
        }

        io.println(lo);
        io.close();
    }

    /**
     * Returns true if total penalty using given wpm is strictly less than targetT.
     * Early exits if penalty reaches or exceeds targetT.
     */
    private static boolean penaltyLessThan(long[] problems, long wpm, TimePenalty targetT, Kattio io) {
        io.println();
        if (wpm <= 0)
            throw new IllegalArgumentException("wpm must be > 0");
        TimePenalty tPenalty = new TimePenalty();

        for (long p : problems) {
            tPenalty.addWords(p, wpm);
            io.printf("\ntargetMillis: %d ourMillis: %d\n", targetT.totalMillis, tPenalty.totalMillis);
            if (tPenalty.totalMillis >= targetT.totalMillis) {
                return false;
            }
        }
        return true;
    }

    private static class TimePenalty {
        private long totalMillis;

        static long millisFromWords(long numWords, long wpm) {
            // Guard
            if (wpm <= 0)
                throw new IllegalArgumentException("wpm must be > 0");
            // Rounded to nearest millisecond
            long scaled = numWords * 60000L;
            // Optional: check for overflow (scaled < 0 if overflowed)
            if (numWords != 0 && scaled / numWords != 60000L) {
                throw new ArithmeticException("Word count too large");
            }
            return (scaled + wpm / 2) / wpm;
        }

        void addWords(long numWords, long wpm) {
            this.totalMillis += millisFromWords(numWords, wpm);
        }

        void addFromSec(long sec) {
            this.totalMillis += sec * 1000;
        }

        long secondsPart() {
            return totalMillis / 1000;
        }

        long millisPart() {
            return totalMillis % 1000;
        }

        @Override
        public String toString() {
            return secondsPart() + "s " + String.format("%03dms", millisPart());
        }
    }

    // private static boolean penaltyLessThan(long[] problems, long wpm, long
    // targetT, Kattio io) {
    // io.println();
    // double[] timepenalties = new double[problems.length];
    // double tPenalty = 0;
    //
    // timepenalties[0] = ((double) problems[0] / (double) wpm);
    // tPenalty += timepenalties[0];
    //
    // for (int i = 1; i < timepenalties.length; i++) {
    // timepenalties[i] = timepenalties[i - 1] + ((double) problems[i] / (double)
    // wpm);
    // tPenalty += timepenalties[i];
    //
    // io.printf("tPenalty: %f ith p: %f wpm: %d\n", tPenalty, timepenalties[i],
    // wpm);
    // if (tPenalty >= targetT)
    // return false; // Not strictly less
    // }
    // return true; // total < targetT
    // }
}
