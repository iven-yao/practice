package leetcode;
import java.util.*;

public class q989 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();

        int carry = 0;
        for(int i = num.length-1; i >= 0; i--) {
            int tmp = num[i] + k%10 + carry;
            k = k/10;
            ans.add(0,tmp%10);
            carry = tmp/10;
        }

        while(k > 0 || carry > 0) {
            int tmp = k%10+carry;
            ans.add(0, tmp%10);
            carry = tmp/10;
            k = k/10;
        }
        return ans;
    }
}