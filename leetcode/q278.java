package leetcode;


public class q278 {

    static int first;

    public boolean isBadVersion(int n) {
        return n>=first;
    }
    

    public int firstBadVersion(int n) {
        int l = 1, r = n;

        while(l < r) {
            int i = l/2+r/2;
            if(isBadVersion(i)) r = i;
            else l = i+1;
        }

        return l;

    }

    public static void main(String[] args) {
        first = 1702766719;
        System.out.println(new q278().firstBadVersion(2126753390));
    }
}
