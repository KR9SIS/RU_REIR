import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int[] items = new int[n];

        for (int i = 0; i < n; i++) {
            items[i] = io.getInt();
        }
        Arrays.sort(items);
        int part = n / 3;

        for (int i = 0; i < part; i++) {
            int tmp = items[i];
            items[i] = items[i + part];
            items[i + part] = tmp;
        }

        io.printf("%d", items[0]);
        for (int i = 1; i < n; i++) {
            io.printf(" %d", items[i]);
        }
        io.close();
    }
}
