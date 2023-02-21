package leetcode;

public class q1979 {
    public int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int num: nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }

        return gcd(min, max);
    }

    public int gcd(int a, int b) {
        return (b==0)? a: gcd(b, a%b);
    }
}
