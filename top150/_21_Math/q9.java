package top150._21_Math;

public class q9 {
    public boolean isPalindrome(int x) {
        int ori = x;
        int rev = 0;
        while(x > 0) {
            rev *= 10;
            rev += x%10;
            x /= 10;
        }

        return rev == ori;
    }
}
