import java.util.Arrays;

public class Main {
    static class Guess {
        String contestant;
        int guess;
    }

    public static void main(String[] args) {
        KattIO io = new KattIO(System.in, System.out);
        int n = io.getInt();
        Guess[] guesses = new Guess[n];

        // Getting contestants
        for (int i = 0; i < n; i++) {
            guesses[i] = new Guess();
            guesses[i].contestant = io.getWord();
            guesses[i].guess = io.getInt();
        }
        Arrays.sort(guesses, (a, b) -> Integer.compare(a.guess, b.guess));

        // Getting ideas
        n = io.getInt();
        int[] ideas = new int[n];
        for (int i = 0; i < n; i++) {
            ideas[i] = io.getInt();
        }

        // Getting winners
        for (int idea : ideas) {
            Guess g = binarySearch(idea, guesses, io);
            if (g != null) {
                io.printf("%s\n", g.contestant);
            } else {
                io.println(":(");
            }
        }
        io.close();
    }

    private static Guess binarySearch(int target, Guess[] arr, KattIO io) {
        int low = 0, high = arr.length - 1, resIdx = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid].guess <= target) {
                resIdx = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return resIdx == -1 ? null : arr[resIdx];
    }
}
