package top150._01_ArrayString;

public class q12 {
    String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < values.length; i++) {
            int val = values[i];
            while(num >= val) {
                sb.append(romans[i]);
                num-=val;
            }
        }

        return sb.toString();
    }
}
