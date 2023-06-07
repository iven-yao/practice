package leetcode;

public class q1318 {
    class Solution {
        public int minFlips(int a, int b, int c) {
            String aa = Integer.toBinaryString(a);
            String bb = Integer.toBinaryString(b);
            String cc = Integer.toBinaryString(c);
    
            // System.out.println(aa);
            // System.out.println(bb);
            // System.out.println(cc);
            
            int flip = 0;
            // check if aa, bb longer than cc
            for(int i = 0; i < aa.length()-cc.length(); i++) {
                if(aa.charAt(i) == '1') flip++;
            }
            for(int i = 0; i < bb.length()-cc.length(); i++) {
                if(bb.charAt(i) == '1') flip++;
            }
            for(int i = 0; i < cc.length(); i++) {
                char aaa = '0';
                char bbb = '0';
                char ccc = cc.charAt(cc.length()-i-1);
                if(i < aa.length()) {
                    aaa = aa.charAt(aa.length()-i-1);
                }
                if(i < bb.length()) {
                    bbb = bb.charAt(bb.length()-i-1);
                }
    
                if(ccc == '0') {
                    if(aaa == '1') flip++;
                    if(bbb == '1') flip++;
                } else {
                    if(aaa == '0' && bbb == '0') flip++;
                }
            }
    
            return flip;
        }
    }

    class BetterSolution {
        public int minFlips(int a, int b, int c) {
            int flip = 0;
    
            while(a > 0 || b > 0 || c > 0) {
                int bitA = a&1;
                int bitB = b&1;
                int bitC = c&1;
    
                if(bitC == 0) {
                    if(bitA == 1) flip++;
                    if(bitB == 1) flip++;
                } else {
                    if(bitA+bitB == 0) flip++;
                }
    
                a >>= 1;
                b >>= 1;
                c >>= 1;
            }
    
            return flip;
        }
    }
}
