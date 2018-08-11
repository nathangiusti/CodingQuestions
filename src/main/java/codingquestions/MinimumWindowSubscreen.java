package codingquestions;

import java.util.ArrayList;
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

        char[] array = str.toCharArray();

        int retVal = str.length() + 1;
        int end = -1;

        ArrayList<Character> charArr = new ArrayList<Character>();
        ArrayList<Character> currArr = new ArrayList<Character>();

        for (char c : charSet.toCharArray())
            charArr.add(c);

        HashMap<Character, Integer> charMap = arrayToMap(charArr);
        HashMap<Character, Integer> currMap = new HashMap<Character, Integer>();
        
        //We're only gonna track characters in the character set
        for(Character c : charMap.keySet())
            currMap.put(c, 0);

        for (int i = 0; i < str.length(); i++) {
            currArr.add(array[i]);
            
            //If it's in the character set, update the currmap, else skip
            if(charMap.get(array[i]) != null)
                currMap.put(array[i], currMap.get(array[i]) + 1);
            else
                continue;

            //Go to the beginning of the string and remove any characters that either:
            //Aren't in the charMap
            //Appear more frequently than they are in the charmap
            while (charMap.get(currArr.get(0)) == null || currMap.get(currArr.get(0)) > charMap.get(currArr.get(0))) {
                if(charMap.get(currArr.get(0)) != null)
                    currMap.put(currArr.get(0), currMap.get(currArr.get(0)) - 1);
                currArr.remove(0);
            }

            //Check if our current string contains charmap
            if (hasCompleteSet(currMap, charMap)) {
                //Just track the last index of the string and it's length 
                if (retVal > currArr.size()) {
                    retVal = currArr.size();
                    end = i;
                }
            }
        }
        if(end < 0)
            return "";
        
        String retStr = "";
        for (int i = end - retVal + 1; i <= end; i++)
            retStr += array[i];
        return retStr;
    }

    private static HashMap<Character, Integer> arrayToMap(ArrayList<Character> charArr) {
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        for (Character c : charArr)
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
