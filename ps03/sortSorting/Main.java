public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        if (n != 0) {
            sortCase(io, n);
            n = io.getInt();
        }
        while (n != 0) {
            io.println();
            sortCase(io, n);

            n = io.getInt();
        }
        io.close();
    }

    private static void sortCase(Kattio io, int n) {
        String[] testCase = new String[n];
        for (int i = 0; i < n; i++) {
            testCase[i] = io.getWord();
        }
        // Insertion Sort
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (testCase[j].substring(0, 2).compareTo(testCase[j - 1].substring(0, 2)) < 0) {
                    // If j < j-1
                    String tmp = testCase[j];
                    testCase[j] = testCase[j - 1];
                    testCase[j - 1] = tmp;
                }
            }

        }
        for (String name : testCase) {
            io.println(name);
        }

    }
}
