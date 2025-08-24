public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int days = io.getInt();
        int maxTime = Integer.MAX_VALUE;
        int sumTime = 0;
        int times[] = new int[days];

        for (int i = 0; i < days; i++) {
            int time = io.getInt();
            times[i] = time;
        }

        for (int i = days - 1; i >= 0; i--) {
            if (times[i] < maxTime) {
                maxTime = times[i];
            }
            sumTime += maxTime;
        }

        io.println(sumTime);
        io.close();
    }
}
