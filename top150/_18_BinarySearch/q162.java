package top150._18_BinarySearch;

public class q162 {
    public int findPeakElement(int[] nums) {
        int l = 0;
        int r = nums.length -1;

        while(l < r) {
            int mid = (l+r)/2;

            if(nums[mid] < nums[mid+1]) l = mid+1;
            else r = mid;
        }

        return l;
    }

    public int findPeakElementWorse(int[] nums) {
        if(nums.length == 1) return 0;

        int left = 0;
        int right = nums.length-1;
        // System.out.println("--");
        while(left < right) {
            int mid = left + (right-left)/2;
            // System.out.println(mid);
            if(mid == 0) {
                if(nums[mid+1] < nums[mid]) right = mid;
                else left = mid+1;
            } else if(mid == nums.length-1) {
                if(nums[mid-1] > nums[mid]) right = mid;
                else left = mid+1;
            } else {
                if(nums[mid+1] > nums[mid]) left = mid+1;
                // else if(nums[mid-1] > nums[mid]) right = mid;
                else right = mid;
            }
        }

        return right;
    }
}
