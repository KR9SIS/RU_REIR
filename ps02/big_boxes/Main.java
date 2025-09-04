public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int k = io.getInt();
        int lo = 0, hi = 0;
        int[] items = new int[n];

        for (int i = 0; i < n; i++) {
            int w = io.getInt();
            if (w > lo) {
                lo = w;
            }
            hi += w;
            items[i] = w;
        }

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canSplit(items, k, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        io.printf("%d\n", lo);
        io.close();
    }

    private static boolean canSplit(int[] items, int k, int maxWeight) {
        int boxesUsed = 1; // start with one box
        int currentSum = 0;
        for (int weight : items) {
            if (currentSum + weight > maxWeight) {
                boxesUsed++;
                currentSum = weight;
                if (boxesUsed > k)
                    return false;
            } else {
                currentSum += weight;
            }
        }
        return true;
    }
}
