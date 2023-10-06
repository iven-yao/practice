package top150.ArrayString;

import java.util.Arrays;

public class q274 {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);

        int h = 0;
        for(int i = citations.length-1; i >= 0; i--) {
            if(citations[i] > h) h++;
            else break; 
        }

        return h;
    }
}
