package top150._20_BitManipulation;

public class q201 {
    public int rangeBitwiseAnd(int left, int right) {
        while(right > left) {
            right = right & (right-1);
        }

        return left&right;
    }
}
