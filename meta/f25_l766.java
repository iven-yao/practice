package meta;

public class f25_l766 {
    class Solution {
        public boolean isToeplitzMatrix(int[][] matrix) {
            
            // check first row
            for(int i = 0; i < matrix[0].length; i++) {
                int val = matrix[0][i];
                for(int j = 1; j < matrix.length && j+i < matrix[0].length; j++) {
                    if(matrix[j][j+i] != val) return false;
                }
            }
    
            // check first col
            for(int i = 1; i < matrix.length; i++) {
                int val = matrix[i][0];
                for(int j = 1; j < matrix[0].length && j+i < matrix.length; j++) {
                    if(matrix[i+j][j] != val) return false;
                }
            }
    
            return true;
        }
    }
}
