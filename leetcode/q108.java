package leetcode;
import node_definition.TreeNode;

public class q108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length);
    }

    public TreeNode helper(int[] nums, int l, int r) {
        if(l>=r) return null;
        int mid = l + (r-l)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, l, mid);
        node.right = helper(nums, mid+1, r);

        return node;
    }
}
