package codingquestions;

import java.util.ArrayList;

public class Anagrams {

    public static void main(String[] args) {
        String str1 = "cat";
        String str2 = "act";
        
       boolean ret = isAnagram(str1, str2);
       
       System.out.println(ret);
    }
    
    public static boolean isAnagram(String str1, String str2)
    {
        if (str1.length() != str2.length())
            return false;
        
        //convert strings to arrays
        ArrayList<Character> strArr1 = new ArrayList<Character>();
        ArrayList<Character> strArr2 = new ArrayList<Character>();
        
        for(char c : str1.toCharArray())
            strArr1.add(c);
        
        for(char c : str2.toCharArray())
            strArr2.add(c);
        
        //Iterate through one array and remove the chars from other array
        for(Character c : strArr1)
        {
            if(strArr2.contains(c))
                strArr2.remove(c);
            else
                return false;
        }
        
        return true;
    }
}

