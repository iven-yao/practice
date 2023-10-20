package top150._07_Stack;

import java.util.Stack;

public class q150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for(String token: tokens) {
            if(token.equals("+")){
                int later = stack.pop();
                int former = stack.pop();
                stack.push(former+later);
            }
            else if(token.equals("-")){
                int later = stack.pop();
                int former = stack.pop();
                stack.push(former-later);
            }
            else if(token.equals("*")){
                int later = stack.pop();
                int former = stack.pop();
                stack.push(former*later);
            }
            else if(token.equals("/")){
                int later = stack.pop();
                int former = stack.pop();
                stack.push(former/later);
            } else {
                stack.push(Integer.valueOf(token));
            }
        }

        return stack.pop();
    }
}
