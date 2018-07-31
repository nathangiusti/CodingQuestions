package codingquestions;

import java.util.*;

public class MaxUniqueSubstring {

     public static void main(String []args){
        String str = "pwwkew";
       
        int i = maxSub(str);
       
        System.out.println(i);
       
     }
    
     private static int maxSub(String str)
     {
         char[] array = str.toCharArray();
        
         int ret = 0;
        
         ArrayList<Character> currArr = new ArrayList<Character>();
        
         for(int i = 0; i < str.length(); i++)
         {
             
             //If currArr has the character
             if(currArr.indexOf(array[i]) > -1)
             {
                 //rep marks the character we are scanning now already resides
                 int rep = currArr.indexOf(array[i]);
                 
                 //update the return value is the currArr is greater in size
                 ret = ret > currArr.size() ? ret : currArr.size();
                 
                 //add the new char on
                 currArr.add(array[i]);
                 
                 //remove everything that came before the repeated char from the array
                 for(int j = 0; j < rep + 2; j++)
                 {
                     currArr.remove(0);
                 }
             } else
                currArr.add(array[i]);
         }
         //needed in case the last string is the largest
         ret = ret > currArr.size() ? ret : currArr.size();
        
         return ret;
     }
}