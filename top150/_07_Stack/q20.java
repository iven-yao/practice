package top150._07_Stack;

import java.util.Stack;

public class q20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(Character c: s.toCharArray()){
            if(c == ')') {
                if(stack.isEmpty()) return false;
                if(stack.pop() != '(')  return false;
            } else if( c == '}') {
                if(stack.isEmpty()) return false;
                if(stack.pop() != '{')  return false;
            } else if( c == ']') {
                if(stack.isEmpty()) return false;
                if(stack.pop() != '[')  return false;
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();

    }
}
