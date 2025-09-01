import java.lang.Math;

public class Main {
    // Newton-Raphson approximation for Lambert W (for positive real x)
    // private static double lambertW(double x) {
    // double w = Math.log(x + 1); // initial guess
    // for (int i = 0; i < 100; i++) {
    // double ew = Math.exp(w);
    // double wew = w * ew;
    // double delta = (wew - x) / (ew * (w + 1));
    // w -= delta;
    // if (Math.abs(delta) < 1e-10)
    // break;
    // }
    // return w;
    // }
    private static double lambertW(double x) {
        // Double lambertW using Hally's method
        double w = Math.log(x + 1); // initial guess
        for (int i = 0; i < 100; i++) {
            double ew = Math.exp(w);
            double wew = w * ew;
            double t = wew - x;
            double denominator = ew * (w + 1) - ((w + 2) * t) / (2 * (w + 1));
            double delta = t / denominator;
            w -= delta;
            if (Math.abs(delta) < 1e-10)
                break;
        }
        return w;
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        double c = io.getDouble();
        double x = 1e6 * c * Math.log(10); // 10^6 * c * ln(10)
        double w = lambertW(x);
        int n = (int) Math.exp(w);
        io.println(n);
        io.close();
    }
}

// log10(n)/10**6
