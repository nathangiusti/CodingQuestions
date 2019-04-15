/*
 * Create a function to add integers that are represented as strings in binary.
 *
 * For example: "1001" + "11" = "1100"
 */

public class AddingBinaryNumbers {
	
	public static void main(String args[])
	{
		assert addNumbers("1001", "11") == "1100" : "Test failed";
		assert addNumbers("101010", "11011") == "1000101" : "Test failed";
		assert addNumbers("11", "1001") == "1100" : "Test failed";
		
		System.out.println("Tests passed");
	}
	
	public static String addNumbers(String num1, String num2)
	{
		String ret = "";
		
		//Make num1 the larger and pad out the smaller so they are the same length
		if(num2.length() > num1.length())
		{
			String temp = num2;
			num2 = num1;
			num1 = temp;
		}
		
		while(num2.length() < num1.length())
			num2 = '0' + num2;
		
		boolean carry = false;
		for(int i = 0; i < num2.length(); i++)
		{
			Character char1 = num1.charAt(num1.length() - i - 1);
			Character char2 = num2.charAt(num2.length() - i - 1);
			if(char1 == '0' && char2 == '0')
			{
				if(carry)
					ret = '1' + ret;
				else
					ret = '0' + ret;
				
				carry = false;
			}
			else if(char1 == '1' && char2 == '1')
			{
				if(carry)
					ret = '1' + ret;
				else
					ret = '0' + ret;
				carry = true;
			} else {
				if(carry)
					ret = '0' + ret;
				else
					ret = '1' + ret;
			}
		}
		if(carry)
			ret = '1' + ret;
		return ret;
	}

}
