// import edu.princeton.cs.algs4.StdIn;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();

        String bestGuest = "";
        int bestGift = 0;
        for (int i = 0; i < n; i++) {
            String guest = io.getWord();
            int guestGift = io.getInt();
            if (guestGift > bestGift) {
                bestGuest = guest;
                bestGift = guestGift;

            }
        }
        io.println(bestGuest);
        io.close();
    }
}
