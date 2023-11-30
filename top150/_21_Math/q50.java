package top150._21_Math;

public class q50 {
    public double myPow(double x, int n) {
        return helper(x, n);
    }

    public double helper(double x, long n) {
        if(n == 1) return x;
        if(n == 0) return 1;
        if(n < 0) {
            x = 1/x;
            n = -n; 
        }

        return (n%2==0)? helper(x*x, n/2) : x*(helper(x*x, n/2));
    }
}
