package leetcode;

public class q287 {
    //O(n) space O(n) time
    // public int findDuplicate(int[] nums) {
    //     int[] count = new int[nums.length];

    //     for(int num: nums){
    //         count[num]++;
    //         if(count[num] == 2) return num;
    //     }

    //     return -1;
    // }
    //O(1) space O(nlogn) time
    public int findDuplicate(int[] nums) {
        int l = 0, r = nums.length-1;
        int mid, count;
        while(l < r) {
            // System.out.println(l+","+r);
            mid = l+(r-l)/2;
            count = 0;
            for(int num: nums) {
                if(num <= mid) {
                    count++;
                }
            }

            if(count <= mid) {
                l = mid+1;
            } else {
                r = mid;
            }
        }

        return l;
    }
}
