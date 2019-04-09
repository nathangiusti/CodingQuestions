package main.java.codingquestions;

public class ReverseStringNoMem {
    
    public static void main(String[] args) {
        String str = "You are a cool guy";
        
       String ret = reverseSentence(str);
       
       System.out.println(ret);
    }
    
    private static String reverseSentence(String str) {
        str = str.trim();
        
        //Represents how much we've reversed so far
        int i = 0;
        while(true)
        {
            //Find the last word in the string
            int space = str.lastIndexOf(" ");
            int size = str.length() - space;
            
            //if i + size >= length we are looking at the last word so break
            if(i + size >= str.length())
                break;
            
            // the part we've already reversed + the previous last word + the rest of the string to reverse minus the word we moved
            str = str.substring(0, i) + str.substring(space + 1, str.length()) + " " + str.substring(i, space);
            
            //Move our pointer forward
            i += size;
        }
        
        return str;
    }

}
