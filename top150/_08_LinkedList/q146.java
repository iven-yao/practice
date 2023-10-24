package top150._08_LinkedList;

import java.util.HashMap;
import java.util.Map;

public class q146 {
    class LRUCache {

        private class Node {
            int key;
            int value;
            Node prev;
            Node next;

            Node(int k, int v) {
                this.key = k;
                this.value = v;
            }

            Node() {
                this.key = 0;
                this.value = 0;
            }
        }

        private Map<Integer, Node> cache;
        private int cap;
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            cache = new HashMap<>();
            cap = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }
        
        public int get(int key) {
            Node node = cache.get(key);
            if(node == null) {
                return -1;
            }

            remove(node);
            add(node);

            return node.value;
            
        }
        
        public void put(int key, int value) {
            Node node = cache.get(key);
            if(node == null) {
                node = new Node(key, value);
                cache.put(key, node);
                add(node);
            } else {
                node.value = value;
                remove(node);
                add(node);
            }
            if(cache.size() > cap) {
                Node eldest = head.next;
                remove(eldest);
                cache.remove(eldest.key);
            }
        }

        private void add(Node node) {
            Node last = tail.prev;
            last.next = node;
            node.prev = last;
            node.next = tail;
            tail.prev = node;
        }

        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
}
