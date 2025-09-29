import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int allies = io.getInt();

        int[] enemies = new int[n];
        for (int i = 0; i < n; i++) {
            enemies[i] = io.getInt();
        }
        Arrays.sort(enemies);

        for (int i = 0; i < n; i++)
            io.printf("Enemy: %d\n", enemies[i]);

        io.printf("Allies: %d\n", allies);
        int count = 0;
        for (int enemy : enemies) {
            allies -= (enemy + 1);
            if (allies < 0) {
                break;
            }
            count++;
            io.printf("Wins: %d ", count);
            io.printf("Enemy: %d ", enemy);
            io.printf("Allies: %d\n", allies);
        }
        io.println(count);
        io.close();
    }
}
