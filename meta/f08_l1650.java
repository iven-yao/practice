package meta;

import java.util.HashSet;
import java.util.Set;

public class f08_l1650 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    class Solution {
        public Node lowestCommonAncestor(Node p, Node q) {
            Set<Node> set = new HashSet<>();
            while(p != null) {
                set.add(p);
                p = p.parent;
            }

            while(q != null) {
                if(!set.add(q)) {
                    return q;
                }

                q = q.parent;
            }

            return null;
        }
    }
}
