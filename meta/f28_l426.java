package meta;

import java.util.ArrayList;
import java.util.List;

import node_definition.TreeNode;

public class f28_l426 {
    class InPlaceSolution {
        class DLL {
            TreeNode head;
            TreeNode tail;
            public DLL() {
                head = new TreeNode(0);
                tail = head;
            }
        }
        /**
         * @param root: root of a tree
         * @return: head node of a doubly linked list
         */
        public TreeNode treeToDoublyList(TreeNode root) {
            // Write your code here.
            if(root == null) return null;
            
            DLL dll = new DLL();
            connect(root, dll);
            dll.tail.right = dll.head.right;
            dll.head.right.left = dll.tail;
    
            return dll.head.right;
        }
    
        private void connect(TreeNode node, DLL dll) {
            if(node == null) return;
    
            connect(node.left, dll);
    
            dll.tail.right = node;
            node.left = dll.tail;
            dll.tail = node;
    
            connect(node.right, dll);
        }
    }
    
    class Solution {
        /**
         * @param root: root of a tree
         * @return: head node of a doubly linked list
         *
         **/
        public TreeNode treeToDoublyList(TreeNode root) {
            // Write your code here
            // do in order traverse to get the correct order
            List<Integer> order = new ArrayList<>();
            helper(root, order);

            TreeNode dummy = new TreeNode(0);
            TreeNode prev = dummy;

            for(int val: order) {
                prev.right = new TreeNode(val);
                prev.right.left = prev;
                prev = prev.right;
            }

            prev.right = dummy.right;
            dummy.right.left = prev;

            return dummy.right;
        }

        private void helper(TreeNode node, List<Integer> order) {
            if(node.left != null) helper(node.left, order);
            order.add(node.val);
            if(node.right!= null) helper(node.right, order);
        }
    }
}
