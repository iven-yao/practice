package CoreSkill;

public class DynamicArray {
    int[] array;
    int tailPtr;
    public DynamicArray(int capacity) {
        array = new int[capacity];
        tailPtr = 0;
    }

    public int get(int i) {
        return array[i];
    }

    public void set(int i, int n) {
        array[i] = n;
    }

    public void pushback(int n) {
        if(tailPtr == array.length) resize();

        array[tailPtr++] = n;
    }

    public int popback() {
        return array[--tailPtr];
    }

    private void resize() {
        int[] tmp = array;
        array = new int[tmp.length*2];
        for(int i = 0; i < tmp.length; i++) {
            array[i] = tmp[i];
        }
    }

    public int getSize() {
        return tailPtr;
    }

    public int getCapacity() {
        return array.length;
    }
}
