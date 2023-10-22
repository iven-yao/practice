package top150._08_LinkedList;

import java.util.HashMap;
import java.util.Map;

public class q138 {
    class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    
    public Node copyRandomList(Node head) {
        Node dummy = new Node(0);
        Node ptr = head;
        Node copyPtr = dummy;
        Map<Node, Node> cache = new HashMap<>();

        while(ptr != null) {
            cache.putIfAbsent(ptr, new Node(ptr.val));
            copyPtr.next = cache.get(ptr);
            copyPtr = copyPtr.next;
            if(ptr.next != null){
                cache.putIfAbsent(ptr.next, new Node(ptr.next.val));
                copyPtr.next = cache.get(ptr.next);
            }
            if(ptr.random != null) {
                cache.putIfAbsent(ptr.random, new Node(ptr.random.val));
                copyPtr.random = cache.get(ptr.random);
            }
            ptr = ptr.next;
        }

        return dummy.next;
    }
}
