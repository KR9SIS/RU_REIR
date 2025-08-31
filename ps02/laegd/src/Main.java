public class Main {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int n = io.getInt();
        int m = io.getInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = io.getInt();
            }
        }
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if ((matrix[i][j] < matrix[i + 1][j]) && // below
                        (matrix[i][j] < matrix[i - 1][j]) && // above
                        (matrix[i][j] < matrix[i][j - 1]) && // left
                        (matrix[i][j] < matrix[i][j + 1])) { // right
                    io.println("Jebb");
                    io.flush();
                    return;
                }
            }
        }
        io.println("Neibb");
        io.flush();
    }
}
