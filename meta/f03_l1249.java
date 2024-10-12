package meta;

import java.util.PriorityQueue;
import java.util.Stack;

public class f03_l1249 {
    public String minRemoveToMakeValidOptimize(String s) {
        int open = 0;
        int close = 0;

        for(char c: s.toCharArray()) {
            if(c == '(') {
                open++;
            }
            if(c == ')') {
                if(open > 0) {
                    open--;
                } else {
                    close++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        // remove close parentheses from the head
        for(char c: s.toCharArray()) {
            if(close > 0 && c == ')') {
                close--;
            } else {
                sb.append(c);
            }
        }
        // remove open parenthese from the end
        s = sb.reverse().toString();
        sb = new StringBuilder();
        for(char c: s.toCharArray()) {
            if(open > 0 && c == '(') {
                open--;
            } else {
                sb.append(c);
            }
        }

        return sb.reverse().toString();
    }

    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        PriorityQueue<Integer> toBeRemoved = new PriorityQueue<>((a,b) -> a-b);

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(') {
                stack.push(i);
            }

            if(c == ')') {
                if(stack.isEmpty()) {
                    toBeRemoved.offer(i);
                } else {
                    stack.pop();
                }
            }
        }

        while(!stack.isEmpty()) {
            toBeRemoved.offer(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        int start = 0;
        while(!toBeRemoved.isEmpty()) {
            int removeIndex = toBeRemoved.poll();
            sb.append(s.substring(start, removeIndex));
            start = removeIndex+1;
        }
        sb.append(s.substring(start, s.length()));
        return sb.toString();
    }
}
