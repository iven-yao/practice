package meta;

public class f04_l408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        // write your code here
        // like internationalization -> i18n

        int iWord = 0;
        int iAbbr = 0;

        while(iWord < word.length() && iAbbr < abbr.length()) {
            int skip = 0;
            // number can't be led by 0
            if(abbr.charAt(iAbbr) == '0') {
                break;
            }
            while(iAbbr < abbr.length() && Character.isDigit(abbr.charAt(iAbbr))) {
                skip *= 10;
                skip += abbr.charAt(iAbbr) - '0';
                iAbbr++;
            }

            if(skip>0) {
                iWord += skip;
            } else {
                if(word.charAt(iWord) == abbr.charAt(iAbbr)) {
                    iWord++;
                    iAbbr++;
                } else {
                    break;
                }
            }
        }

        return iWord==word.length() && iAbbr==abbr.length();
    }
}
