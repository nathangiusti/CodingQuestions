/* Given three strings - A, B, C - determine if C is a valid interleaved
combination of A and B. The characters within A and B should appear in their
original order, but they can be interleaved arbitrarily (e.g. all of A"s
characters can come first, or we can alternate, all of B"s can come first,
etc.).
 
Example:
A = "abc"
B = "def"
 
 
C = "adbecf" => ok (a <d> b <e> c <f>)
C = "abcdef" => ok
C = "defabc" => ok
C = "adcebf" => not ok (A"s chars appear out of order: "c" comes b */

public class InterleavedStrings {
	
	public static void main(String args[])
	{
		assert isInterleaved("abc", "def", "abcdef") == true : "Test failed";
		assert isInterleaved("abc", "def", "defabc") == true : "Test failed";
		assert isInterleaved("abc", "def", "adbecf") == true : "Test failed";
		assert isInterleaved("ab", "ac", "abac") == true : "Test failed";
		assert isInterleaved("ab", "ac", "acab") == true : "Test failed";
		assert isInterleaved("aaaa", "aaaa", "aaaaaaaa") == true : "Test failed";
		assert isInterleaved("aaaa", "aaab", "aaabaaaa") == true : "Test failed";
		assert isInterleaved("", "", "") == true : "Test failed";
		assert isInterleaved("abaac", "aabca", "aabaabacca") == true : "Test failed";
		 
		assert isInterleaved("abc", "def", "adcebf") == false : "Test failed";
		assert isInterleaved("ab", "ac", "acba") == false : "Test failed";
		assert isInterleaved("abc", "def", "abcde") == false : "Test failed";
		assert isInterleaved("abc", "def", "abcdefc") == false : "Test failed";
		assert isInterleaved("aaab", "aaaa", "baaaaaaa") == false : "Test failed";
		assert isInterleaved("aaaa", "aaab", "aabaaaaa") == false : "Test failed";
		assert isInterleaved("abaac", "aabac", "aabaabacca") == false : "Test failed";
		
		System.out.println("Tests passed");
		 
	}
	
	public static boolean isInterleaved(String str1, String str2, String interleave)
	{
		//Check exit conditions
		if(str1.isEmpty() && str2.isEmpty())
			return true;
		
		if(interleave.isEmpty() && (!str1.isEmpty() || (!str2.isEmpty())))
			return false;
		
		//DFS for interleaved strings. 
		if(interleave.startsWith(str1.substring(0, 1)))
		{
			if(isInterleaved(str1.substring(1), str2, interleave.substring(1)))
				return true;
		} else if(interleave.startsWith(str2.substring(0, 1))) {
			if(isInterleaved(str1, str2.substring(1), interleave.substring(1)))
				return true;
		} else
			return isInterleaved(str1, str2, interleave.substring(1));
		
		return false;
	}

}
