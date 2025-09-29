import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int[] a = new int[] { 4, 3, 1, 7, 11, 10 };
        if (a.length < 3) {
            return;
        }
        Arrays.sort(a);
        int curDist = 0, maxDist = 0, bestBuy = 0;
        for (int i = 1; i < a.length - 1; i++) {
            curDist = (a[i + 1]) - (a[i - 1]);
            io.printf("%d - %d = %d\n", a[i + 1], a[i - 1], curDist);

            if (maxDist < curDist) {
                maxDist = curDist;
                bestBuy = a[i];
            }
        }

        io.dumpIntArr(a);
        io.printf("bestBuy: %d", bestBuy);

        io.close();
    }
}
