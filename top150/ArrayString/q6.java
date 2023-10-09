package top150.ArrayString;

import java.util.ArrayList;
import java.util.List;

public class q6 {
    public String convert(String s, int numRows) {
        if(numRows == 1)  return s;
        if(s.length() <= numRows) return s;

        int cycle = numRows*2 -2;

        List<StringBuilder> sbs = new ArrayList<>();
        for(int i = 0; i < numRows; i++) {
            sbs.add(new StringBuilder());
        }

        int index = 0;
        while(index < s.length()) {
            int remainder = index%cycle;
            if(remainder >= numRows) remainder = cycle-remainder;

            sbs.get(remainder).append(s.charAt(index++));
        }

        for(int i = 1; i < numRows; i++) {
            sbs.get(0).append(sbs.get(i));
        }

        return sbs.get(0).toString();
    }
}
