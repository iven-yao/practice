package top150._01_ArrayString;

import utils.util;

// 88. Merge Sorted Array
public class q88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while(n>0 || m>0) {
            if(m == 0) {
                nums1[n-1] = nums2[n-1];
                n--;
            } else if(n == 0) {
                m--;
            } else if(nums1[m-1] >= nums2[n-1]) {
                nums1[m+n-1] = nums1[m-1];
                m--;
            } else {
                nums1[m+n-1] = nums2[n-1];
                n--;
            }
        }
        
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = new int[]{2,5,6};
        int n = 3;
        
        new q88().merge(nums1, m, nums2, n);
        util.printArray(nums1);

    }
}
