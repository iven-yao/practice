package leetcode;

public class q1071 {
    public String gcdOfStrings(String str1, String str2) {
        if(str1.equals(str2)) return str1;
        
        int gcd = gcd(str1.length(), str2.length());
        String prefix = str1.substring(0, gcd);
        if(prefix.equals(str2.substring(0, gcd))) {
            boolean checkstr1 = true;
            boolean checkstr2 = true;
            for(int i = gcd; i+gcd <= str1.length(); i+=gcd) {
                if(!str1.substring(i, i+gcd).equals(prefix)) {
                    checkstr1 = false;
                    break;
                }
            }
            for(int i = gcd; i+gcd <= str2.length(); i+=gcd) {
                if(!str2.substring(i, i+gcd).equals(prefix)) {
                    checkstr2 = false;
                    break;
                }
            }

            if(checkstr1 && checkstr2) return prefix;
        }
        return "";

    }

    // Euclidean Algorithm
    public int gcd(int a, int b) {
        return (b == 0)? a : gcd(b, a%b);
    }

    public static void main(String[] args) {
        q1071 q = new q1071();

        System.out.println(q.gcdOfStrings("ABCABCABC", "ABCABC"));
    }
}
