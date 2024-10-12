package CoreSkill;
import java.util.ArrayList;

public class LinkedList {

    class Node {
        int val;
        Node next;

        Node(int v, Node n) {
            val = v;
            next = n;
        }

        Node(int v) {
            val = v;
            next = null;
        }
    }

    Node head;
    Node tail;

    public LinkedList() {
        head = new Node(-1);
        tail = head;
    }

    public int get(int index) {
        Node node = head.next;
        while(node != null && index > 0) {
            node = node.next;
            index--;
        }

        return node == null? -1 : node.val;
    }

    public void insertHead(int val) {
        Node tmp = head.next;
        head.next = new Node(val, tmp);
        if(head == tail) tail = head.next;
    }

    public void insertTail(int val) {
        tail.next = new Node(val);
        tail = tail.next;
    }

    public boolean remove(int index) {
        Node node = head;
        while(node != null && index > 0) {
            node = node.next;
            index--;
        }

        if(node != null && node.next != null) {
            if(node.next == tail) {
                tail = node;
            }
            node.next = node.next.next;
            return true;
        }

        return false;
    }

    public ArrayList<Integer> getValues() {
        Node node = head.next;
        ArrayList<Integer> res = new ArrayList<>();
        while(node != null) {
            res.add(node.val);
            node = node.next;
        }

        return res;
    }
}
