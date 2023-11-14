package top150._18_BinarySearch;

public class q35 {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while(left < right) {
            int mid = (left + right)/2;

            if(nums[mid] > target) right = mid;
            else if(nums[mid] < target) left = mid+1;
            else return mid;
        }

        return left;
    }
}
