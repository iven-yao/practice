package interview;
import java.util.*;

public class SubArrayDiffSum {
    // give an integer array, calculate the sum of all the max and min value difference in subarrays
    // [1,4,1] expected output = 9
    // explanation:
    // [1] => 1-1 = 0
    // [1,4] => 4-1 = 3
    // [1,4,1] => 4-1 = 3
    // [4] => 4-4 = 0
    // [4, 1] => 4-1 = 3
    // [1] => 1-1 = 0

    // TLE in some longer cases
    // Thoughts?
    public static int diffSum(List<Integer> nums) {
        int ans = 0;
        for(int i = 0; i < nums.size(); i++) {
            int max = nums.get(i);
            int min = nums.get(i);
            for(int j = i; j < nums.size(); j++) {
                System.out.println("["+i+","+j+"]");
                max = Math.max(max, nums.get(j));
                min = Math.min(min, nums.get(j));
                ans += max-min;
            }
            
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(diffSum(Arrays.asList(new Integer[]{1,4,1})));
    }
}
