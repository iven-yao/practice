package meta;

import java.util.Stack;

public class f10_l227 {
    class Solution {
        final int ADD = 0;
        final int SUBSTRACT = 1;
        final int MULTIPLY = 2;
        final int DIVIDE = 3;

        public int calculate(String s) {
            Stack<Integer> stack = new Stack<>();

            int index = 0;
            int op = ADD;
            while(index < s.length()) {
                char c = s.charAt(index);
                if(Character.isDigit(c)) {
                    int number = 0;
                    while(index < s.length() && Character.isDigit(s.charAt(index))) {
                        number *= 10;
                        number += s.charAt(index) - '0';
                        index++;
                    }

                    switch(op) {
                        case ADD:
                            break;
                        case SUBSTRACT:
                            number *= -1;
                            break;
                        case MULTIPLY:
                            number *= stack.pop();
                            break;
                        case DIVIDE:
                            number = stack.pop() / number;
                            break;
                        default:
                            // throw new Exception("Invalid Operation: " + op);
                    }

                    stack.push(number);
                } else {
                    if(c == '+') {
                        op = ADD;
                    } else if(c == '-') {
                        op = SUBSTRACT;
                    } else if(c == '*') {
                        op = MULTIPLY;
                    } else if(c == '/') {
                        op = DIVIDE;
                    }
                    index++;
                }
            }

            int sum = 0;
            while(!stack.isEmpty()) {
                sum += stack.pop();
            }

            return sum;
        }
    }
}
