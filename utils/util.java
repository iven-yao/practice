package utils;

public class util {
    
    public static void printArray(int[] arr) {
        System.out.print("["+arr[0]);
        for(int i = 1; i < arr.length; i++) {
            System.out.print(","+arr[i]);
        }
        System.out.println("]");
    }
}
