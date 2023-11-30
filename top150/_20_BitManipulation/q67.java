package top150._20_BitManipulation;

public class q67 {
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
}
