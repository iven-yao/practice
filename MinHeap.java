import java.util.*;

class MinHeap {

    Queue<Integer> pQueue;

    public MinHeap() {
        pQueue = new PriorityQueue<Integer>();
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
        MinHeap mHeap = new MinHeap();

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