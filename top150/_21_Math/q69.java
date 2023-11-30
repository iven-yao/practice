package top150._21_Math;

public class q69 {
    public int mySqrt(int x) {
        int left = 0;
        int right = x;

        while(left <= right) {
            int mid = left + (right - left)/2;
            if(mid == 0) return right;
            if(mid == x/mid) return mid;
            if(mid > x/mid) right = mid-1;
            else left = mid+1;
        }

        return right;
    }
}
