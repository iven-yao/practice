package top150._04_Matrix;

public class q48 {
    public void rotate(int[][] matrix) {
        // by observation, rotate 90 degree clockwise = transpose + mirror horizontally
        int n = matrix.length;
        // transpose
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }

        // mirror horizontally
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n/2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = tmp;
            }
        }
    }
}
