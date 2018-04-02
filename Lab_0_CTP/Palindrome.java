public class Palindrome 
{
	public static void main(String[] args) 
	{
		for (int i = 0; i < args.length; i++) 
		{
			String s = args[i];
			String s1 = reverseString(s);
			if (s1.equals(s))
				{System.out.println("Слово"+s+"-палиндром");}
			else
				{System.out.println("Слово"+s+"-не палиндром");}
		}
	}
	public static String reverseString(String s)
	{
		String rev="";
		for (int i = s.length()-1; i >= 0; i--)
			{rev+=s.charAt(i);}
		return rev; 
	}
}
