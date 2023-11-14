package top150._18_BinarySearch;

public class q34 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;

        int[] res = new int[]{-1,-1};
        if(nums.length == 0) return res;

        // find the left one
        while(left <= right) {
            int mid = left + (right-left)/2;
            
            if(nums[mid] == target) {
                res[0] = mid;
                right = mid - 1;
            } else if(nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }

        left = 0;
        right = nums.length-1;
        // find the right one
        while(left <= right) {
            int mid = left + (right-left)/2;
            if(nums[mid] == target) {
                res[1] = mid;
                left = mid + 1;
            } else if(nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }

        return res;
    }    
}
