package leetcode;
import java.util.*;
import node_definition.*;

public class q109 {
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
    
        while(head != null) {
            list.add(head.val);
            head = head.next;
        }

        return helper(list, 0, list.size());
    }

    public TreeNode helper(List<Integer> list, int l, int r) {
        TreeNode node = null;
        if(l < r) {
            node = new TreeNode();
            int mid = l+(r-l)/2;

            node.val = list.get(mid);
            node.left = helper(list, l, mid);
            node.right = helper(list, mid+1, r);
        }

        return node;
    }
}
