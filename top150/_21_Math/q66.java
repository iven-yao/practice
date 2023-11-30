package top150._21_Math;

public class q66 {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        int i = digits.length-1;

        while(i >= 0 && carry == 1){
            if(carry == 1){
                digits[i]++;
                carry = 0;
            }
            if(digits[i] == 10) {
                digits[i] = 0;
                carry = 1;
            }
            i--;
        }

        if(carry == 1) {
            int[] ans = new int[digits.length+1];
            ans[0] = 1;
            for(int j = 1; j < digits.length+1; j++) {
                ans[j] = digits[j-1];
            }

            return ans;
        }

        return digits;
    }
}
