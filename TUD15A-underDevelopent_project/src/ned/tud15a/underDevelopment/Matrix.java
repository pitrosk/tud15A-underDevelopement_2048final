package ned.tud15a.underDevelopment;


final public class Matrix {
    private final int M =4;             // number of rows
    private final int N =4;             // number of columns
    public final int[][] data;   // M-by-N array

    public Matrix() {
        data = new int[M][N];
    }
    Matrix copyMatrix( ) {
        Matrix target = new Matrix();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                target.data[i][j] = data[i][j];
            }
        }
        return target;
    }
    public boolean equalMatrix(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }
}
