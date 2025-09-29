public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        // int[] a = new int[] { 2, 5, 8, 10, 10, 7, 4, 1 };
        int[] a = new int[] { 2, 3, 4, 9, 10, 9, 4, 3, 2 };
        int[] sortedA = new int[a.length];
        int idx = 0;
        int i = 0;
        int j = a.length - 1;
        while (i < j) {
            for (int p = 0; p < a.length; p++) {
                io.printf("%d ", sortedA[p]);
            }
            io.println();
            if (a[i] < a[j]) {
                sortedA[idx] = a[i];
                idx++;
                i++;
            } else {
                sortedA[idx] = a[j];
                idx++;
                j--;
            }
            io.flush();

        }
        sortedA[a.length - 1] = a[i];

        for (int p = 0; p < a.length; p++) {
            io.printf("%d ", sortedA[p]);
        }
        io.println();

        io.close();
    }
}
