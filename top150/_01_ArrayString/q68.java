package top150._01_ArrayString;

import java.util.ArrayList;
import java.util.List;

public class q68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        int lettersCnt = 0;
        for(String s: words) {
            if(cur.size() + s.length() + lettersCnt > maxWidth) {
                // full, add whitespace in between and put in res
                int spaceInBetween = cur.size() - 1;
                int spaceCnt = maxWidth - lettersCnt;
                StringBuilder sb = new StringBuilder();
                for(String ss: cur) {
                    int spaces = 0;
                    if(spaceInBetween > 0){
                        spaces = spaceCnt/spaceInBetween + (spaceCnt%spaceInBetween == 0? 0: 1);
                    }
                    sb.append(ss);
                    sb.append(spaces(spaces));
                    spaceInBetween--;
                    spaceCnt -= spaces;
                }
                sb.append(spaces(spaceCnt));
                res.add(sb.toString());
                cur.clear();
                lettersCnt = 0;
            }

            cur.add(s);
            lettersCnt += s.length();
        }

        // last line
        int spaceCnt = maxWidth - lettersCnt;
        StringBuilder sb = new StringBuilder();
        for(String ss: cur) {
            sb.append(ss);
            if(spaceCnt-- > 0) sb.append(" ");
        }
        sb.append(spaces(spaceCnt));
        res.add(sb.toString());

        return res;
    }

    private String spaces(int spaces) {
        StringBuilder sb = new StringBuilder();
        while(spaces-- > 0) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
