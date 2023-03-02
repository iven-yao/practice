package leetcode;
import java.util.*;

public class q443 {
    int i = 0;
    public int compress(char[] chars) {
        int count = 0;
        char curr = chars[0];

        for(char c : chars) {
            if(curr == c) {
                count++;
            } else {
                chars[i++] = curr;
                if(count > 1) {
                    helper(chars, count);
                }
                curr = c;
                count = 1;
            }
        }

        chars[i++] = curr;
        if(count > 1) {
            helper(chars, count);
        }

        return i;

    }

    public void helper(char[] chars, int count){
        Stack<Character> stack = new Stack<>();
        while(count > 0) {
            int mod = count%10;
            count /= 10;
            stack.push(itoc(mod));
        }

        while(!stack.isEmpty()) {
            chars[i++] = stack.pop();
        }
    }

    public char itoc(int i) {
        switch(i){
            case 0: return '0';
            case 1: return '1';
            case 2: return '2';
            case 3: return '3';
            case 4: return '4';
            case 5: return '5';
            case 6: return '6';
            case 7: return '7';
            case 8: return '8';
            case 9: return '9';
            default: return 'x';
        }
    }
}
