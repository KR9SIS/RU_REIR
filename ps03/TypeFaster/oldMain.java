import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        long topTPenalty = io.getInt();

        long wpm = 1;
        long[] problems = new long[n];

        // Get and sort input problems in ascending order
        for (int i = 0; i < n; i++) {
            problems[i] = io.getInt();
        }
        Arrays.sort(problems);
        long tPenalty = calcTimePenalty(problems, 0, wpm);

        // Double wpm until we have a lower time penalty than the top team
        while (topTPenalty < tPenalty) {
            tPenalty = calcTimePenalty(problems, tPenalty, wpm);

            if (topTPenalty < tPenalty) {
                wpm *= 2;
            }
        }
        // Work backwards until we know the lowest wpm where we lose to the top team
        while (topTPenalty >= tPenalty) {
            wpm -= 1;
            tPenalty = calcTimePenalty(problems, tPenalty, wpm);
        }
        // Increase our wpm so we beat them
        wpm += 1;

        io.println(wpm);
        io.close();
    }

    private static long calcTimePenalty(long[] problems, long tPenalty, long wpm) {
        long[] timepenalties = new long[problems.length];
        timepenalties[0] = (problems[0] / wpm);
        for (int i = 1; i < timepenalties.length; i++) {
            timepenalties[i] = timepenalties[i - 1] + (problems[i] / wpm);
        }

        tPenalty = 0;
        for (long penalty : timepenalties) {
            tPenalty += penalty;
        }
        return tPenalty;
    }
}
