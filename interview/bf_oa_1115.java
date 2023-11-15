package interview;

import java.util.Stack;

public class bf_oa_1115 {
    public static String reverse(String str) {
        Stack<Character> s =  new Stack<>();

        for(char c: str.toCharArray()) {
            s.push(c);
        }

        StringBuilder sb = new StringBuilder();

        while(!s.isEmpty()) {
            sb.append(s.pop());
        }

        return sb.toString();
    }

    public static String longest(String str) {

        int maxLen = 0;
        int idx = 0;
        String[] words = str.split(" ");

        for(int i = 0; i < words.length; i++) {
            int len = 0;
            for(char c: words[i].toCharArray()) {
                if(Character.isLetterOrDigit(c)) len++;
            }
            if(len > maxLen) {
                maxLen = len;
                idx = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c: words[idx].toCharArray()) {
            if(Character.isLetterOrDigit(c)) sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverse("I love dogs"));
        System.out.println(longest("I lo1234ve!!! d$$$ogss"));
    }
}
