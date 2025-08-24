public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();

        io.println("+" + "-".repeat(n) + "+");
        for (int i = 0; i < n; i++) {
            io.println("|" + " ".repeat(n) + "|");
        }
        io.println("+" + "-".repeat(n) + "+");

        io.close();
    }
}
