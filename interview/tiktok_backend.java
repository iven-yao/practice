package interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class tiktok_backend {
    // Maximum value obtained by performing given operations in an Array
    /*
     * Given an array arr[], the task is to find out the maximum obtainable value. 
     * The user is allowed to add or multiply the two consecutive elements. 
     * However, there has to be at least one addition operation between two multiplication operations (i.e), 
     * two consecutive multiplication operations are not allowed.
     * Let the array elements be 1, 2, 3, 4 
     * then 1 * 2 + 3 + 4 is a valid operation 
     * whereas 1 + 2 * 3 * 4 is not a a valid operation as there are consecutive multiplication operations.
     */

     public static int findMax(int[] arr) {
        int[][] dp = new int[arr.length][2];

        dp[0][0] = arr[0];
        dp[0][1] = 0;

        for(int i = 1; i < arr.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]) + arr[i];
            dp[i][1] = dp[i-1][0] - arr[i-1] + arr[i-1]*arr[i];
        }

        int max = Math.max(dp[arr.length-1][0], dp[arr.length-1][1]);
        backTracking(arr, dp, arr.length-1, max);

        return max;
     }

     public static void backTracking(int[] arr, int[][] dp, int index, int val) {
        if(index < 0) return;
        if(val == dp[index][0]) {
            backTracking(arr, dp, index-1, val-arr[index]);
            if(index != 0) System.out.print("+");
            System.out.print(String.format("%d", arr[index]));
        } else {
            backTracking(arr, dp, index-2, val-arr[index]*arr[index-1]);
            if(index != 0) System.out.print("+");
            System.out.print(String.format("%d*%d", arr[index-1], arr[index]));
        }
     }

     // remove minimum invalid parantheses and return the String
     // b(y(t)e))dance => b(y(t)e)dance
     // (tik()tok => tik()tok or (tik)tok
     public static String removeInvalidParantheses(String str) {
        Stack<Integer> leftp = new Stack<>();
        Set<Integer> removeIdx = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        char[] cArr = str.toCharArray();

        for(int i = 0; i < cArr.length; i++) {
            if(cArr[i] == '(') leftp.push(i);
            if(cArr[i] == ')') {
                if(leftp.size() > 0) leftp.pop();
                else removeIdx.add(i);
            }
        }

        while(leftp.size() > 0) removeIdx.add(leftp.pop());

        for(int i = 0; i < cArr.length; i++) {
            if(!removeIdx.contains(i)) sb.append(cArr[i]);
        }
        
        return sb.toString();
     }

     public static void main(String[] args) {
        System.out.println("findMax------------------------------------\n");
        int[][] testcases = {
            {1,1,1,1},
            {1,2,3,1},
            {1,2,3,4,5,6,7,6,5,4,3,2,1}
        };

        for(int[] testcase: testcases) {
            System.out.println(Arrays.toString(testcase));
            System.out.println(" = "+ findMax(testcase));
        }

        System.out.println("\nfindMax------------------------------------");
        System.out.println("removeInvalidParantheses-------------------\n");

        String[] teststrs = {"b(y(t)e))dance","(tik()tok"};
        for(String teststr: teststrs) {
            System.out.print(String.format("%s => ", teststr));
            System.out.println(removeInvalidParantheses(teststr));
        }

        System.out.println("\nremoveInvalidParantheses-------------------");
     }
}
