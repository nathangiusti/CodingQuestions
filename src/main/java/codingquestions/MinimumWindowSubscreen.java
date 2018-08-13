package codingquestions;

import java.util.HashMap;
import java.util.Set;

// https://leetcode.com/problems/minimum-window-substring/description/

public class MinimumWindowSubscreen {

    public static void main(String margv[]) {
        
        test("ADOBECODEBANC", "ABC", "BANC");
        test("ADDDDDDDADBC", "ABC", "ADBC");
        test("aaaaaabbbbbcdd", "abcdd", "abbbbbcdd");
        test("ab", "b", "b");
        test("aa", "a", "a");
        test("a", "aa", "");

    }

    private static void test(String str, String charStr, String ans) {
        String res = minimumSubString(str, charStr);
        if (!res.equals(ans)) {
            System.out.println("Input String: " + str);
            System.out.println("Input CharSet: " + charStr);
            System.out.println("Expected: " + ans);
            System.out.println("Got: " + res);
        }

    }

    public static String minimumSubString(String str, String charSet) {

        int retVal = str.length() + 1;
        int end = -1;

        int strBegin = 0;

        HashMap<Character, Integer> charMap = arrayToMap(charSet);
        HashMap<Character, Integer> currMap = new HashMap<Character, Integer>();

        // We're only gonna track characters in the character set
        for (Character c : charMap.keySet())
            currMap.put(c, 0);

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // If it's in the character set, update the currmap, else skip
            if (charMap.get(c) != null)
                currMap.put(c, currMap.get(c) + 1);
            else
                continue;

            // Go to the beginning of the string and remove any characters that either:
            // Aren't in the charMap
            // Appear more frequently than they are in the charmap

            c = str.charAt(strBegin);
            while (charMap.get(c) == null || currMap.get(c) > charMap.get(c)) {
                if (charMap.get(c) != null)
                    currMap.put(c, currMap.get(c) - 1);
                strBegin++;
                c = str.charAt(strBegin);
            }

            // Check if our current string contains charmap
            if (retVal > i - strBegin + 1 && hasCompleteSet(currMap, charMap)) {
                retVal = i - strBegin + 1;
                end = i;
            }
        }
        if (end < 0)
            return "";

        return str.substring(end - retVal + 1, end + 1);
    }



    private static HashMap<Character, Integer> arrayToMap(String charSet) {
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        for (char c : charSet.toCharArray())
            if (charMap.get(c) == null)
                charMap.put(c, 1);
            else
                charMap.put(c, charMap.get(c) + 1);
        return charMap;
    }

    private static boolean hasCompleteSet(HashMap<Character, Integer> currMap, HashMap<Character, Integer> charMap) {
        Set<Character> keySet = charMap.keySet();
        for (Character c : keySet)
            if (currMap.get(c) == null || currMap.get(c) < charMap.get(c))
                return false;

        return true;
    }
}
