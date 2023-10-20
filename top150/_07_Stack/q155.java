package top150._07_Stack;

import java.util.Stack;

public class q155 {
    class MinStack {

        Stack<int[]> stack;
        int currMin;

        public MinStack() {
            stack = new Stack<>();
            currMin = Integer.MAX_VALUE;
        }
        
        public void push(int val) {
            currMin = Math.min(currMin, val);
            stack.push(new int[]{val, currMin});
        }
        
        public void pop() {
            stack.pop();
            if(stack.isEmpty()) currMin = Integer.MAX_VALUE;
            else currMin = stack.peek()[1];
        }
        
        public int top() {
            return stack.peek()[0];
        }
        
        public int getMin() {
            return stack.peek()[1];
        }
    }
}
