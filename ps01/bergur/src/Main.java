public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int days = io.getInt();
        int yesterdayTime = 0;
        int sumTime = 0;

        for (int i = 0; i < days; i++) {
            int time = io.getInt();
            if (time >= yesterdayTime) {
                sumTime += time;
            }
            yesterdayTime = time;
        }
        io.println(sumTime);
        io.close();
    }
}

// Alg
// Get number of days
// For every day
// add it to the sum
// Check if it's less than the previous one
// If it is break
