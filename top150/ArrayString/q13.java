package top150.ArrayString;

public class q13 {
    public int romanToInt(String s) {
        char prev = 'A';
        int total = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int currVal = c2v(c);
            int prevVal = c2v(prev);
            if(currVal > c2v(prev)) {
                total += currVal - prevVal * 2;
            } else {
                total += currVal;
            }
            prev = c;
        }

        return total;
    } 

    private int c2v(char c) {
        if (c == 'I') return 1;
		if (c == 'V') return 5;
		if (c == 'X') return 10;
		if (c == 'L') return 50;
		if (c == 'C') return 100;
		if (c == 'D') return 500;
		if (c == 'M') return 1000;
		return 0;
    }
}
