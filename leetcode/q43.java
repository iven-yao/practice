package leetcode;

public class q43 {

    public String add(String num1, String num2) {
        // System.out.println("num1 = " + num1 + ", num2 = " + num2);
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        StringBuilder sb = new StringBuilder();
        while(i >= 0 || j >= 0 || carry > 0) {
            int i1 = (i < 0)? 0 : num1.charAt(i) - '0';
            int i2 = (j < 0)? 0 : num2.charAt(j) - '0';
            int sum = i1+i2+carry;
            sb.append(sum%10);
            carry = sum/10;
            i--;
            j--;
        }

        return sb.reverse().toString();
    }

    public String multiply(String num1, String num2) {
        String res = "0";
        if(num1.equals("0") || num2.equals("0")) return res;

        for(int i = num1.length()-1; i >= 0; i--) {
            int n1 = (num1.charAt(i)-'0');
            StringBuilder sb = new StringBuilder();
            int a = num1.length()-1-i;
            while(a > 0) {
                a--;
                sb.append(0);
            }
            int carry = 0;
            for(int j = num2.length()-1; j >= 0; j--) {
                int n2 = (num2.charAt(j)-'0');
                int sum = n1*n2+carry;
                sb.append(sum%10);
                carry = sum/10;          
            }
            if(carry > 0) sb.append(carry);
            // System.out.println("sb = " + sb.toString());
            res = add(res, sb.reverse().toString());
            // System.out.println("res = " + res);
        }

        return res;
    }


    public static void main(String[] args) {
        System.out.println(new q43().multiply("12345", "54321"));
    }
}
