package Main;

public class ValidPalindrome1 {
	
	public static void main(String[] args) {
		String s = "A man, a plan, a canal: Panama";
		
		System.out.println(new ValidPalindrome1().isPalindrome(s));
	}
	
	public boolean isPalindrome(String s) {
		String s1 = s.toLowerCase();
		String s2 = "";
		
		for(int i=0; i<s1.length(); i++) {
			if(s1.charAt(i) != ' ' && s1.charAt(i) != ',' && s1.charAt(i) != ':') {
				s2 += s1.charAt(i);
			}
		}
		
		for(int i=0; i<s2.length()/2; i++) {
			if(s2.charAt(i) != s2.charAt(s2.length() - i - 1)) {
				return false;
			}
		}
		
		return true;
	}
	
}
