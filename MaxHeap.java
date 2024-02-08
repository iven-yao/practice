import java.util.*;

class MaxHeap {

    Queue<Integer> pQueue;

    public MaxHeap() {
        pQueue = new PriorityQueue<Integer>((a,b)->b-a);
    }

    public void add(Integer i) {
        pQueue.add(i);
    }

    public Integer pop() {
        return pQueue.poll();
    }

    public Integer peek() {
        return pQueue.peek();
    }

    public void printAll() {
        for(Integer ele: pQueue) {
            System.out.print(ele+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MaxHeap mHeap = new MaxHeap();

        mHeap.add(1);
        mHeap.add(6);
        mHeap.add(3);
        mHeap.printAll();
        mHeap.add(4);
        mHeap.add(5);
        mHeap.add(10);
        mHeap.printAll();
        mHeap.add(99);
        mHeap.printAll();
        System.out.println(mHeap.pop());
        mHeap.printAll();


    }
}