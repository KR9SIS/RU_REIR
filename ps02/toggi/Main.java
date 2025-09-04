import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        long sec = io.getLong();
        long hi = 100000;
        long lo = 1;
        long ans = 0;

        double running_time = calc(hi);
        while (sec >= running_time) {
            running_time = calc(hi);
            hi *= 2;
            // io.printf("runtime = %f\nhi = %f\n", running_time, hi);
            // io.flush();
        }

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            double time = calc(mid);
            // io.printf("l = %f\nm = %f\nh = %f\nret = %f\n", lo, mid, hi, ret);
            // io.flush();
            if (time <= sec) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        io.printf("%d\n", ans);
        io.close();
    }

    private static double calc(double num) {
        return (num * Math.log10(num) / Math.pow(10, 6));
    }
}
