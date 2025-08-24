public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = io.getInt();
        }
        for (int i = n - 1; i >= 0; i--) {
            io.println(numbers[i]);
        }
        io.close();
    }
}
