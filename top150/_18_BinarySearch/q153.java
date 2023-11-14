package top150._18_BinarySearch;

public class q153 {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length-1;

        while(left < right) {
            int mid = left + (right-left)/2;
            // if the middle one we observing is smaller than the right one, 
            // then we are sure about that the minimum won't happen in the right part of pivot
            // we could then update right ptr to mid
            if(nums[mid] < nums[right]) right = mid;
            // if the middle one we observing is larger than the right one,
            // then we are sure that the minimum won't happen in the left part of pivot, 
            // because any k degree rotation was applied to the array
            // we should then move left ptr to mid+1 to investigate the right part of the array for minimum
            else left = mid + 1;
        }

        return nums[left];
    }
}
