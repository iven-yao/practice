package leetcode;
import java.util.*;

public class q67 {

    // beat 100%
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int carry = 0;
        for(int i = 0; i < a.length() || i < b.length() || carry == 1; i++) {
            int aa = (a.length()-i-1 >= 0) ? a.charAt(a.length()-i-1) - '0':0;
            int bb = (b.length()-i-1 >= 0) ? b.charAt(b.length()-i-1) - '0':0;
            int sum = aa + bb + carry;
            sb.append(sum%2);
            carry = sum/2;
        }

        return sb.reverse().toString();
    }

    public String addBinarySLOW(String a, String b) {
        Stack<Character> aa = new Stack<>();
        Stack<Character> bb = new Stack<>();
        Stack<Character> res = new Stack<>();
        for(Character c: a.toCharArray()) {
            aa.push(c);
        }

        for(Character c: b.toCharArray()) {
            bb.push(c);
        }

        int carry = 0;
        while(!aa.isEmpty() || !bb.isEmpty()) {
            int aaa = 0;
            int bbb = 0;
            if(!aa.isEmpty()) aaa = aa.pop() - '0';
            if(!bb.isEmpty()) bbb = bb.pop() - '0';
            
            int tmp = aaa + bbb + carry;
            if(tmp == 0) {
                res.push('0');
                carry = 0;
            } else if(tmp == 1) {
                res.push('1');
                carry = 0;
            } else if(tmp == 2) {
                res.push('0');
                carry = 1;
            } else {
                res.push('1');
                carry = 1;
            }
        }

        if(carry == 1) {
            res.push('1');
        }

        StringBuilder sb = new StringBuilder();
        while(!res.isEmpty()) {
            sb.append(res.pop());
        }

        return sb.toString();
    }
}
