package top150._18_BinarySearch;

public class q4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        // make sure nums1 length < nums2 length
        if(n1 > n2) return findMedianSortedArrays(nums2, nums1);

        int n = n1 + n2;
        int leftSize = (n+1)/2;
        int l = 0, r = n1;

        while(l <= r) {
            int mid1 = (l+r)/2;
            int mid2 = leftSize - mid1;

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if (mid1 < n1)
                r1 = nums1[mid1];
            if (mid2 < n2)
                r2 = nums2[mid2];
            if (mid1 - 1 >= 0)
                l1 = nums1[mid1 - 1];
            if (mid2 - 1 >= 0)
                l2 = nums2[mid2 - 1];

            // Check if this partition is valid by verifying 
            // if the largest number on the left side is smaller than the smallest number on the right side.   
            if(l1 <= r2 && l2 <= r1) {
                if(n % 2 == 1) {
                    return Math.max(l1, l2);
                } else {
                    return ((double)Math.max(l1, l2) + Math.min(r1, r2))/2.0;
                }
            } else if (l1 > r2) {
                r = mid1 - 1;
            } else {
                l = mid1 + 1;
            }
        }

        // should never reach this point
        return -1;
        
    }
}
