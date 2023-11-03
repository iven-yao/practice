package top150._09_BinaryTreeGeneral;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q117 {
    class dfs {
        public Node connect(Node root) {
            Map<Integer, List<Node>> map = new HashMap<>();
            dfsHelper(root, 0, map);
            for (List<Node> list : map.values())
                for (int i = 1; i < list.size(); i++)
                    list.get(i - 1).next = list.get(i);
            return root;
        }

        private void dfsHelper(Node node, int lvl, Map<Integer, List<Node>> map) {
            if (node == null)
                return;

            List<Node> list = map.computeIfAbsent(lvl, ArrayList::new);
            list.add(node);
            lvl++;
            dfsHelper(node.left, lvl, map);
            dfsHelper(node.right, lvl, map);
        }
    }

    class bfs {
        public Node connect(Node root) {
            if (root == null)
                return root;

            List<List<Node>> list = new ArrayList<>();
            List<Node> prev = new ArrayList<>();
            prev.add(root);
            list.add(prev);

            while (!prev.isEmpty()) {
                List<Node> next = buildList(prev);
                list.add(next);
                prev = next;
            }

            for (List<Node> l : list) {
                for (int i = 1; i < l.size(); i++) {
                    l.get(i - 1).next = l.get(i);
                }
            }

            return root;
        }

        private List<Node> buildList(List<Node> prev) {
            List<Node> curr = new ArrayList<>();
            for (Node n : prev) {
                if (n.left != null)
                    curr.add(n.left);
                if (n.right != null)
                    curr.add(n.right);
            }
            return curr;
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };
}
