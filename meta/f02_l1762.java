package meta;

import java.util.Deque;
import java.util.LinkedList;
/*
 * Description
 * Given an array of integers heights of length n, indicating that there are n buildings, heights[i] represents the height of the building at the corresponding position.
 * To the right of a building is the ocean, and for each building, if all buildings to the right of that building are strictly lower than it, then that building has a view of the ocean.
 * Returns a list of indexed subscripts of all buildings with an ocean view, in ascending order, with index subscripts starting at 0.
 */
public class f02_l1762 {

    public int[] findBuildings(int[] heights) {
        int currMax = Integer.MIN_VALUE;
        Deque<Integer> deque = new LinkedList<>();
        for(int i = heights.length-1; i >= 0; i--) {
            if(heights[i] > currMax) {
                deque.offerFirst(i);
                currMax = heights[i];
            }
        }

        return deque.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        
    }
}
