package top150._18_BinarySearch;

public class q74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length-1;
        int n = matrix[0].length-1;

        while(left < right) {
            // System.out.println(1);
            int mid = (left + right)/2;
            if(matrix[mid][n] < target) left = mid+1;
            else right = mid;
        }

        int row = left;
        left = 0;
        right = matrix[0].length-1;

        while(left < right) {
            // System.out.println(2);
            int mid = (left + right)/2;

            if(matrix[row][mid] < target) left = mid+1;
            else right = mid;
        }

        return matrix[row][left] == target;

    }
}
