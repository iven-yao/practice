package interview;

import java.util.*;

public class MinOpsToOneArray {
    // give an integer array, you can change any index value to gcd with its adjacent value in one operation
    // find the minimum operations to make the array a one-array, (means every element is 1)
    // if can't return -1

    static int min = Integer.MAX_VALUE;
    static int stack = 0;

    // apparently wrong, need to find the best (greedy?) way to change value in each iteration...
    public static int minOps(List<Integer> nums) {
        // int ops = 0;
        // while(!is1Array(nums)) {
        //     int maxDiffIndex = -1;
        //     int theDiff = 0;
        //     boolean post = true;

        //     for(int i = 0; i < nums.size()-1; i++) {
        //         int diff = nums.get(i) - gcd(nums.get(i), nums.get(i+1));
        //         if( diff > theDiff) {
        //             theDiff = diff;
        //             maxDiffIndex = i; 
        //         }
        //     }

        //     for(int i = nums.size()-1; i > 0; i--) {
        //         int diff = nums.get(i) - gcd(nums.get(i), nums.get(i-1));
        //         if( diff > theDiff) {
        //             post = false;
        //             theDiff = diff;
        //             maxDiffIndex = i; 
        //         }
        //     }

        //     if(maxDiffIndex != -1) {
        //         if(post) nums.set(maxDiffIndex, gcd(nums.get(maxDiffIndex), nums.get(maxDiffIndex+1)));
        //         else nums.set(maxDiffIndex, gcd(nums.get(maxDiffIndex), nums.get(maxDiffIndex-1)));
        //         ops++;
        //     } else {
        //         return -1;
        //     }
        // }

        // return ops;

        minOps(nums, 0);

        return min==Integer.MAX_VALUE?-1:min;
    }

    // TLE, brute force.
    public static void minOps(List<Integer> nums, int curOps) {
        // System.out.println(nums);
        // System.out.println(curOps);
        System.out.println(stack++);
        if(is1Array(nums)) {
            min = Math.min(min, curOps);
            return;
        }

        if(curOps > nums.size()*2 || curOps > min) return;

        for(int i = 0; i < nums.size()-1; i++) {
            int tmp = nums.get(i);
            if(tmp == 1) continue;
            int gcd = gcd(nums.get(i), nums.get(i+1));
            if(gcd == tmp) continue;
            
            List<Integer> a = new ArrayList<>(nums);
            a.set(i, gcd);
            minOps(a, curOps+1);
            
        }

        for(int i = nums.size()-1; i > 0; i--) {
            int tmp = nums.get(i);
            if(tmp == 1) continue;
            int gcd = gcd(nums.get(i), nums.get(i-1));
            if(gcd == tmp) continue;
            List<Integer> a = new ArrayList<>(nums);
            a.set(i, gcd);
            minOps(a, curOps+1);
        }
    }

    public static boolean is1Array(List<Integer> nums) {
        for(Integer num: nums){
            if(num != 1) return false;
        }

        return true;
    }

    public static int gcd(int a, int b) {
        return (b==0)?a:gcd(b, a%b);
    }

    public static void main(String[] args) {
        System.out.println(minOps(Arrays.asList(new Integer[]{2,2,3,6,2})));
    }
}
