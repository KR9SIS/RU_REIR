import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int[] a = new int[] { 4, 1, 7, 2, 7, 3, 9 };
        Arrays.sort(a);
        boolean ret = true;
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i]) {
                io.println("False");
                ret = false;
                break;
            }
        }
        if (ret) {
            io.println("True");
        }
        io.close();
    }
}
