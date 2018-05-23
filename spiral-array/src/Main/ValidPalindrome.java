package Main;

public class ValidPalindrome {

	public static void main(String[] args) {
		String input = "abcezdcba";
		
		System.out.println(new ValidPalindrome().validate(input));
	}
	
	public boolean validate(String s) {
		boolean is = true;
		
		char[] chs = s.toCharArray();
		for (int i = 0; i < chs.length / 2; i++) {
			if (chs[i] != chs[chs.length - 1 - i]) {
				is = isPalindrome(s, i+1, chs.length - 1 - i) || isPalindrome(s, i, chs.length - 1 - i - 1);
				break;
			}
		}
		
		return is;
	}
	
	public boolean isPalindrome(String s, int iStart, int iStop) {
		char[] chs = s.toCharArray();
		
		for (int i = iStart, j =iStop ; i < j; i++, j--) {
			if(chs[i] != chs[j]) {
				return false;
			}
		}
		
		return true;
	}
	
}
